package ru.netology

import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun correctCalculationForVKPay() {
        val accountType = "VK Pay"
        val previousTransfers = 10_000_00
        val transferAmount = 2000_00
        val expectedResult = 0

        val result = commissionAmount(accountType, previousTransfers, transferAmount)
        assertEquals(expectedResult, result)
    }

    @Test
    fun correctCalculationForMasterMaestroLowLimit() {
        val accountType = "Maestro"
        val previousTransfers = 50000_00
        val transferAmount = 10_000_00
        val expectedResult = 0

        val result = commissionAmount(accountType, previousTransfers, transferAmount)
        assertEquals(expectedResult, result)
    }

    @Test
    fun correctCalculationForMasterMaestroOverLimit() {
        val accountType = "Maestro"
        val previousTransfers = 75000_00
        val transferAmount = 10_000_00
        val expectedResult = fixCommissionOverLimitMasterAndMaestro+(transferAmount*commissionOverLimitMasterAndMaestro).toInt()

        val result = commissionAmount(accountType, previousTransfers, transferAmount)
        assertEquals(expectedResult, result)
    }

    @Test
    fun correctCalculationForVisaMirLessMinCommission() {
        val accountType = "Visa"
        val previousTransfers = 75000_00
        val transferAmount = 1_000_00
        val expectedResult = minCommissionVisaAndMir

        val result = commissionAmount(accountType, previousTransfers, transferAmount)
        assertEquals(expectedResult, result)
    }

    @Test
    fun correctCalculationForVisaMirOverMinCommission() {
        val accountType = "Visa"
        val previousTransfers = 75000_00
        val transferAmount = 10_000_00
        val expectedResult = (transferAmount*commissionVisaAndMir).toInt()

        val result = commissionAmount(accountType, previousTransfers, transferAmount)
        assertEquals(expectedResult, result)
    }
}
