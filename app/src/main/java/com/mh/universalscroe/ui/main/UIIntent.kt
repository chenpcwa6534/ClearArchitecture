package com.mh.universalscroe.ui.main

sealed class UIIntent{
    object Refresh: UIIntent()
    object Next: UIIntent()
    object Research: UIIntent()
}