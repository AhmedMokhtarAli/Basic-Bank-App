package com.example.bankapp.Ui.coustomrs

import java.math.RoundingMode
import java.text.DecimalFormat

class BalanceFormat {
    companion object {
        fun formatBalance(balance: Double): String {
            val balanceFormat = DecimalFormat("###.###")
            balanceFormat.roundingMode = RoundingMode.CEILING
            return balanceFormat.format(balance)
        }
    }
}