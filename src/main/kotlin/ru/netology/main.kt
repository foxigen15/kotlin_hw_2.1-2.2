package ru.netology

const val freeLimitMasterAndMaestro = 75_000_00
const val commissionOverLimitMasterAndMaestro = 0.0065
const val fixCommissionOverLimitMasterAndMaestro = 20_00
const val commissionVisaAndMir = 0.0075
const val minCommissionVisaAndMir = 35_00

fun main() {

    val accountType = "Mir"
    val previousTransfers = 70_000_00
    val transferAmount = 1_000_00

    val commission = commissionAmount(accountType, previousTransfers, transferAmount)
    println("При переводе $transferAmount копеек по карьте $accountType комиссия составит $commission копеек")
}

fun commissionAmount(
    accountType: String = "VK Pay",
    previousTransfers: Int = 0,
    transferAmount: Int
): Int {
    return when (accountType) {
        "Mastercard","Maestro" -> (if(freeLimitMasterAndMaestro-previousTransfers-transferAmount>=0) 0
        else ((previousTransfers+transferAmount-freeLimitMasterAndMaestro)*commissionOverLimitMasterAndMaestro).toInt() + fixCommissionOverLimitMasterAndMaestro)
        "Visa", "Mir" -> (if (transferAmount*commissionVisaAndMir>minCommissionVisaAndMir) (transferAmount*commissionVisaAndMir).toInt() else minCommissionVisaAndMir)
        else ->0
    }
}

