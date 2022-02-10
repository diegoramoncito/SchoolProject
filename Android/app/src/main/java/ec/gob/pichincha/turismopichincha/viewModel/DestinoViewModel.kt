package ec.gob.pichincha.turismopichincha.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ec.gob.pichincha.turismopichincha.data.remote.MainApi
import ec.gob.pichincha.turismopichincha.model.Buscado
import ec.gob.pichincha.turismopichincha.model.Recomendado
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class DestinoViewModel @Inject constructor(
    val mainApi: MainApi
): ViewModel() {

    val evantos = mutableStateListOf<Any>()
    val recomendados = mutableStateListOf<Recomendado>()
    val buscados = mutableStateListOf<Buscado>()

    // HERE YOU CAN SHOW ANY ANIMATION USING THIS LOADING STATE
    val isLoading = mutableStateOf(false)

    init {
        getData()
    }

    fun getData() = viewModelScope.launch {
        isLoading.value = true
        try {
            val data = mainApi.getData()
            evantos.clear()
            recomendados.clear()
            buscados.clear()
            evantos.addAll(data.eventos)
            recomendados.addAll(data.recomendados)
            buscados.addAll(data.buscados)
        } catch (e:Exception){
            Log.e("data",e.message, e)
        }
        isLoading.value = false
    }

}