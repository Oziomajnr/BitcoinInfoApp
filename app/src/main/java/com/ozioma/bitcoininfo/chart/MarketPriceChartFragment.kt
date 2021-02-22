package com.ozioma.bitcoininfo.chart

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.github.mikephil.charting.charts.LineChart
import com.google.android.material.snackbar.Snackbar
import com.ozioma.bitcoininfo.BaseFragment
import com.ozioma.bitcoininfo.R
import com.ozioma.bitcoininfo.UiResult
import com.ozioma.bitcoininfo.databinding.FragmentMarketPriceBinding
import com.ozioma.bitcoininfo.extension.formatTo2DecimalPlaces


class MarketPriceFragment : BaseFragment<FragmentMarketPriceBinding>() {

    override val layout = R.layout.fragment_market_price
    private val viewModel by viewModels<MarketPriceChartViewModel> { appComponent.getViewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appComponent.inject(this)

        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.getMarketPriceData()
        }

        formatChart(binding.chart)


        viewModel.marketPriceLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is UiResult.Success -> {
                    binding.swipeToRefresh.isRefreshing = false
                    binding.currentMarketPriceTitle.text =
                        resources.getString(R.string.current_data, it.data.title)
                    binding.currentMarketPriceValue.text =
                        it.data.getLatestMarketPrice().value.formatTo2DecimalPlaces()
                    binding.chart.data =
                        it.data.toMpChartLineData(resources.getString(R.string.bitcoin_market_price))
                    binding.chart.invalidate()
                }
                is UiResult.Loading -> binding.swipeToRefresh.isRefreshing = true
                is UiResult.Error -> {
                    binding.swipeToRefresh.isRefreshing = false
                    Snackbar.make(
                        binding.chart,
                        getString(R.string.chart_error_notice),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun formatChart(chart: LineChart) {
        chart.xAxis.valueFormatter = TimeStampToDateValueFormatter()
        chart.description.text =
            resources.getString(R.string.bitcoin_market_price_seven_days)
    }
}