package com.C2479785.beebudget.models

import com.C2479785.beebudget.navigation.AppScreens
import com.google.firebase.firestore.QueryDocumentSnapshot
import java.lang.IllegalArgumentException

data class Budget(public var id: String?,
                  public val userId: String?,
                  public val month: Int,
                  public val year: Int,
                  public val subscriptions: Int,
                  public val food: Int,
                  public val groceries: Int,
                  public val transportation: Int,
                  public val entertainment: Int,
                  public val personalCare: Int,
                  public val others: Int,
){
    fun toMap(): MutableMap<String, Any?> {
        return mutableMapOf(
            "id" to this.id,
            "user_id" to this.userId,
            "month" to this.month,
            "year" to this.year,
            "subscriptions" to this.subscriptions,
            "food" to this.food,
            "groceries" to this.groceries,
            "transportation" to this.transportation,
            "entertainment" to this.entertainment,
            "personal_care" to this.personalCare,
            "others" to this.others
        )
    }

    fun getCategoryAmount(category: ExpenseCategories?): Float {
        return when (category?.value) {
            ExpenseCategories.Subscriptions.value -> this.subscriptions.toFloat()
            ExpenseCategories.Food.value -> this.food.toFloat()
            ExpenseCategories.Groceries.value -> this.groceries.toFloat()
            ExpenseCategories.Transportation.value -> this.transportation.toFloat()
            ExpenseCategories.Entertainment.value -> this.entertainment.toFloat()
            ExpenseCategories.PersonalCare.value -> this.personalCare.toFloat()
            ExpenseCategories.Others.value -> this.others.toFloat()
            null -> this.total()
            else -> this.total()
        }
    }

    fun total(): Float {
        return  (this.subscriptions.toFloat()
        + this.food.toFloat()
        +  this.groceries.toFloat()
        +  this.transportation.toFloat()
        +  this.entertainment.toFloat()
        +  this.personalCare.toFloat()
        + this.others.toFloat())
    }

    companion object {
        fun fromQueryDocumentSnapshot(result: QueryDocumentSnapshot): Budget {
            return Budget(
                id = result.get("id").toString(),
                userId = result.get("user_id").toString(),
                month = result.get("month").toString().toInt(),
                year = result.get("year").toString().toInt(),
                subscriptions = result.get("subscriptions").toString().toInt(),
                food = result.get("food").toString().toInt(),
                groceries = result.get("groceries").toString().toInt(),
                transportation = result.get("transportation").toString().toInt(),
                entertainment = result.get("entertainment").toString().toInt(),
                personalCare = result.get("personal_care").toString().toInt(),
                others = result.get("others").toString().toInt()
            )
        }

        fun default(userId: String?, month: Int, year: Int): Budget {
            return Budget(
                id = null,
                userId = userId,
                month = month,
                year = year,
                subscriptions = 50,
                food = 200,
                groceries = 30,
                transportation = 60,
                entertainment = 50,
                personalCare = 50,
                others = 100
            )
        }
    }
}