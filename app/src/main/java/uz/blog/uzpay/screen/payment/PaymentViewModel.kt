package uz.blog.uzpay.screen.payment

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.blog.uzpay.screen.model.CardModel
import uz.blog.uzpay.screen.model.request.PaymentRequest
import uz.blog.uzpay.screen.repository.CardRepository
import uz.blog.uzpay.screen.repository.selead.DataResult
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(val repository: CardRepository): ViewModel() {

    private var _errorLiveData = MutableLiveData<String>()
    var errorLiveData: LiveData<String> = _errorLiveData

    private var _progressLiveData = MutableLiveData<Boolean>()
    var progressLiveData: LiveData<Boolean> = _progressLiveData

    private var _cardData = MutableLiveData<List<CardModel>>()
    var cardData: LiveData<List<CardModel>> = _cardData

    private var _paymentData = MutableLiveData<Any?>()
    var paymentData: LiveData<Any?> = _paymentData

    fun getCards(){
        _progressLiveData.value = true
        viewModelScope.launch {
            when(val r = repository.getCards()){
                is DataResult.Error -> _errorLiveData.value = r.message
                is DataResult.Success -> _cardData.value = r.result!!
            }
            _progressLiveData.value = false
        }
    }

    fun payment(request: PaymentRequest){
        viewModelScope.launch {
            when(val r = repository.payment(request)){
                is DataResult.Error -> _errorLiveData.value = r.message
                is DataResult.Success -> _paymentData.value = r.result
            }
        }
    }

}