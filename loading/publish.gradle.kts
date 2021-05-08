import com.foundation.widget.buildsrc.*

//diy 的不可以再使用 plugin{id()}的方式依赖
apply(plugin = "com.android.library")
apply(plugin = "kotlin-android")

tasks.register("aaaMyTask") {

}
