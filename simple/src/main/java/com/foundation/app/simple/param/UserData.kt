package com.foundation.app.simple.param

import android.os.Parcel
import android.os.Parcelable

/**
 *@Desc:
 *-
 *-
 *create by zhusw on 5/17/21 17:52
 */
data class UserAddress(val city: String = "none", val streetNo: Int = 0) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(city)
        parcel.writeInt(streetNo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserAddress> {
        override fun createFromParcel(parcel: Parcel): UserAddress {
            return UserAddress(parcel)
        }

        override fun newArray(size: Int): Array<UserAddress?> {
            return arrayOfNulls(size)
        }
    }

}


data class UserDesc(val disposition: String = "none", val height: Int = 0) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(disposition)
        parcel.writeInt(height)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserDesc> {
        override fun createFromParcel(parcel: Parcel): UserDesc {
            return UserDesc(parcel)
        }

        override fun newArray(size: Int): Array<UserDesc?> {
            return arrayOfNulls(size)
        }
    }

}
