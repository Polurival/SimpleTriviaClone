package com.github.polurival.stc.coreui

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.github.polurival.stc.coreapi.di.ApiContainer

/**
 *
 *
 * @author Юрий Польщиков on 05.09.2021
 */
abstract class BaseActivity(@LayoutRes layoutId: Int) : AppCompatActivity(layoutId) {

    protected val api by lazy(LazyThreadSafetyMode.NONE) { application as ApiContainer }
}
