package com.lutungkamarsung.dispen.model
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PermissionModel(
    @SerializedName("created_at")
    val createdAt: String? = "",
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("end_date")
    val endDate: String? = "",
    @SerializedName("end_hour")
    val endHour: Int? = 0,
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("img")
    val img: String? = "",
    @SerializedName("permission_type")
    val permissionType: PermissionType? = PermissionType(),
    @SerializedName("permission_type_id")
    val permissionTypeId: Int? = 0,
    @SerializedName("start_hour")
    val startHour: Int? = 0,
    @SerializedName("status")
    val status: Status? = Status(),
    @SerializedName("status_id")
    val statusId: Int? = 0,
    @SerializedName("sub_class")
    val subClass: SubClass? = SubClass(),
    @SerializedName("sub_class_id")
    val subClassId: Int? = 0,
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("updated_at")
    val updatedAt: String? = "",
    @SerializedName("user_detail")
    val userDetail: UserDetail? = UserDetail(),
    @SerializedName("user_id")
    val userId: Int? = 0,
    val days: Int? = 0
) : Parcelable

@Parcelize
data class PermissionType(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("name")
    val name: String? = ""
) : Parcelable

@Parcelize
data class UserDetail(
    @SerializedName("created_at")
    val createdAt: String? = "",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("nis")
    val nis: String? = "",
    @SerializedName("parent_id")
    val parentId: Int = 0,
    @SerializedName("sub_class_id")
    val subClassId: Int? = 0,
    @SerializedName("sub_class")
    val subClass: SubClass? = SubClass(),
    @SerializedName("updated_at")
    val updatedAt: String? = "",
    @SerializedName("user_id")
    val userId: Int? = 0
) : Parcelable

@Parcelize
data class Status(
    @SerializedName("color")
    val color: String? = "",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("name")
    val name: String? = ""
) : Parcelable

@Parcelize
data class SubClass(
    @SerializedName("active_count")
    val activeCount: Int? = 0,
    @SerializedName("class_id")
    val classId: Int? = 0,
    @SerializedName("homeroom_id")
    val homeroomId: Int? = 0,
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("classes")
    val classes: Classes? = Classes(),
    @SerializedName("permission")
    val permission: ArrayList<PermissionModel> = ArrayList()
) : Parcelable

@Parcelize
data class Classes(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("school_id")
    val schoolId: Int? = 0,
    @SerializedName("school")
    val school: School? = School()

) : Parcelable

data class UserModel(
    @SerializedName("created_at")
    val createdAt: String? = "",
    @SerializedName("email")
    val email: String? = "",
    @SerializedName("email_verified_at")
    val emailVerifiedAt: Any? = Any(),
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("level_id")
    val levelId: Int? = 0,
    @SerializedName("token")
    val token: String? = "",
    @SerializedName("updated_at")
    val updatedAt: String? = "",
    @SerializedName("user_detail")
    val userDetail: UserDetail? = UserDetail()
)

@Parcelize
data class School(
    @SerializedName("address")
    val address: String? = "",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("name")
    val name: String? = ""
) : Parcelable