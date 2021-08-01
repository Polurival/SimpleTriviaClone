package com.github.polurival.stc.androidutil

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

/**
 *  Методы расширения для [Fragment]
 *
 * @author Юрий Польщиков on 01.08.2021
 */

fun Fragment.findNavControllerSafely(): NavController? = if (isAdded) findNavController() else null
