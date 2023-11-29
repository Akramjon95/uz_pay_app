package uz.blog.uzpay.screen.add_card

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.blog.uzpay.screen.model.CardModel
import uz.blog.uzpay.screen.model.ServicesModel
import uz.blog.uzpay.screen.model.request.CardRequest
import uz.blog.uzpay.screen.repository.CardRepository
import uz.blog.uzpay.screen.repository.selead.DataResult
import javax.inject.Inject

@HiltViewModel
class AddCardViewModel @Inject constructor(val repository: CardRepository): ViewModel(){

    private var _errorLiveData = MutableLiveData<String>()
    var errorLiveData: LiveData<String> = _errorLiveData

    private var _progressLiveData = MutableLiveData<Boolean>()
    var progressLiveData: LiveData<Boolean> = _progressLiveData

    private var _addCardData = MutableLiveData<CardModel>()
    var addToCardData: LiveData<CardModel> = _addCardData

    private var _successAdedData = MutableLiveData<Boolean>()
    var successAdedData: LiveData<Boolean> = _successAdedData

    private var _servicesModelData = MutableLiveData<List<ServicesModel>>()
    var servicesModelData: LiveData<List<ServicesModel>> = _servicesModelData


     fun addToCard(request: CardRequest){
        _progressLiveData.value = true
        viewModelScope.launch {
            when(val r = repository.addToCard(request)){
                is DataResult.Error -> {
                    _errorLiveData.value = r.message
                    _successAdedData.value = false
                }
                is DataResult.Success -> {
                    _addCardData.value = r.result!!
                    _successAdedData.value = true
                }
            }
            _progressLiveData.value = false
        }
    }

    fun getPaymentList(category_id: Int){
        _progressLiveData.value = true
        viewModelScope.launch {
            when(val r = repository.getPaymentList(category_id)){
                is DataResult.Error -> {
                    _errorLiveData.value = r.message
                }
                is DataResult.Success -> {
                    _servicesModelData.value = r.result!!
                }
            }
            _progressLiveData.value = false
        }
    }
}