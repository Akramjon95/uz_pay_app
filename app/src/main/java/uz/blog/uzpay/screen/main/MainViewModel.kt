package uz.blog.uzpay.screen.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.blog.uzpay.screen.model.CardModel
import uz.blog.uzpay.screen.model.CategoryModel
import uz.blog.uzpay.screen.model.PaymentHistoryModel
import uz.blog.uzpay.screen.repository.MainRepository
import uz.blog.uzpay.screen.repository.selead.DataResult
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository): ViewModel(){

    private var _errorLiveData = MutableLiveData<String>()
    var errorLiveData: LiveData<String> = _errorLiveData

    private var _progressLiveData = MutableLiveData<Boolean>()
    var progressLiveData: LiveData<Boolean> = _progressLiveData

    private var _categoryData = MutableLiveData<List<CategoryModel>>()
    var categoryData: LiveData<List<CategoryModel>> = _categoryData

    private var _cardData = MutableLiveData<List<CardModel>>()
    var cardData: LiveData<List<CardModel>> = _cardData

    private var _paymentHistoryData = MutableLiveData<List<PaymentHistoryModel>>()
    var paymentHistoryData: LiveData<List<PaymentHistoryModel>> = _paymentHistoryData


    fun getCategories(){
        _progressLiveData.value = true
        viewModelScope.launch {
            when(val result = repository.getCategories()){
                is DataResult.Error -> _errorLiveData.value = result.message
                is DataResult.Success -> _categoryData.value = result.result!!
            }
            _progressLiveData.value = false
        }
    }

    fun getCards(){
        viewModelScope.launch {
            when(val r = repository.getCards()){
                is DataResult.Error -> _errorLiveData.value = r.message
                is DataResult.Success -> _cardData.value = r.result!!
            }
        }
    }

    fun getPaymentHistoryList(){
        viewModelScope.launch {
            when(val r = repository.getPaymentHistory()){
                is DataResult.Error -> _errorLiveData.value = r.message
                is DataResult.Success -> _paymentHistoryData.value = r.result!!
            }
        }
    }

}