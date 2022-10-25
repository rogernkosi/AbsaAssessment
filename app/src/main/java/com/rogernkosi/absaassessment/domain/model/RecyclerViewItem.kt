package com.rogernkosi.absaassessment.domain.model

sealed class RecyclerViewItem {
    class SectionItem(val title: String) : RecyclerViewItem()
    class ContentItem(val interval: String, val temp: Double) : RecyclerViewItem()
}