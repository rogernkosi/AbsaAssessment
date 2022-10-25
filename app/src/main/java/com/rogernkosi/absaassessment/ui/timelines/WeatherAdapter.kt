package com.rogernkosi.absaassessment.ui.timelines

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rogernkosi.absaassessment.commons.Constants.VIEW_TYPE_ITEM
import com.rogernkosi.absaassessment.commons.Constants.VIEW_TYPE_SECTION
import com.rogernkosi.absaassessment.databinding.DateItemsBinding
import com.rogernkosi.absaassessment.databinding.IntervalItemsBinding
import com.rogernkosi.absaassessment.domain.model.RecyclerViewItem
import com.rogernkosi.absaassessment.ui.utils.DateUtils
import org.joda.time.DateTime

class WeatherAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var data = listOf<RecyclerViewItem>()

    override fun getItemViewType(position: Int): Int {
        if (data[position] is RecyclerViewItem.SectionItem) {
            return VIEW_TYPE_SECTION
        }
        return VIEW_TYPE_ITEM
    }

    inner class WeatherViewHolder(private val binding: IntervalItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(timeLine: RecyclerViewItem.ContentItem) {
            binding.interval.text = DateUtils.getDateTimeFormat("yyyy-MM-dd HH:mm:ss")
                .print(DateTime.parse(timeLine.interval))
            binding.temperature.text = timeLine.temp.toString().plus("\u2103")
        }
    }

    inner class SectionViewHolder(private val binding: DateItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(weather: RecyclerViewItem.SectionItem) {
            binding.date.text =
                DateUtils.getDateTimeFormat("yyyy-MM-dd").print(DateTime.parse(weather.title))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_SECTION) {
            return SectionViewHolder(
                DateItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
        return WeatherViewHolder(
            IntervalItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]

        if (holder is SectionViewHolder && item is RecyclerViewItem.SectionItem) {
            holder.bind(item)
        }
        if (holder is WeatherViewHolder && item is RecyclerViewItem.ContentItem) {
            holder.bind(item)
        }
    }

    override fun getItemCount() = data.count()
}