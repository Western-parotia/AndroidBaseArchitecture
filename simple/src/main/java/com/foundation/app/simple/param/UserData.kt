package com.foundation.app.simple.param

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

/**
 *@Desc:
 *-
 *-
 *create by zhusw on 5/17/21 17:52
 */
data class UserAddress(val city: String = "shanghai", val streetNo: Int = 32) : Serializable {

}


class UserDesc(val disposition: String = "nice", val height: Int = 180) : Parcelable {

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
