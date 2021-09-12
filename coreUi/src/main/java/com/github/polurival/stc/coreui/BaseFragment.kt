package com.github.polurival.stc.coreui

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.github.polurival.stc.coreapi.di.ApiContainer

/**
 *
 *
 * @author Юрий Польщиков on 05.09.2021
 */
abstract class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {

    protected val api by lazy(LazyThreadSafetyMode.NONE) { requireActivity().application as ApiContainer }
}
