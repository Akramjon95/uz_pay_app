package uz.blog.uzpay.screen.utils

import com.orhanobut.hawk.Hawk
import uz.blog.uzpay.screen.MyApp

object PrefUtils {
    const val PREF_TOKEN = "token"
    const val PREF_ACCOUNT_NUMBER = "account_number"
    const val PREF_PAY_ID = "pay_id"

    fun init(){
        Hawk.init(MyApp.app).build()
    }

    fun setToken(str: String?){
        Hawk.put(PREF_TOKEN, str)
    }

    fun getToken(): String{
        return Hawk.get(PREF_TOKEN, "")
    }

    fun setAccountNumber(number: String?){
        Hawk.put(PREF_ACCOUNT_NUMBER, number)
    }

    fun getAccountNUmber(): String{
        return Hawk.get(PREF_ACCOUNT_NUMBER, "")
    }

    fun setPayId(pay_id: Int?){
        Hawk.put(PREF_PAY_ID, pay_id)
    }
    fun getPayId(): Int{
        return Hawk.get(PREF_PAY_ID, 0)
    }

}