package com.foundation.app.simple.vm

/**
 * create by zhusw on 5/21/21 15:24
 *
count = 1_0000
I/System.out: --反射:无 1 ms
I/System.out: --反射：java 17 ms
I/System.out: --反射：java 带缓存 3 ms
I/System.out: --反射：kotlin 13 ms
I/System.out: --反射：kotlin 带缓存 6 ms
 */
object ReflectionTest {
    fun test() {
        val count = 1_0000
        val nt = System.currentTimeMillis()
        for (i in 0..count) {
            Test.work()
        }
        val r1 = System.currentTimeMillis() - nt
        println("--反射:无 $r1 ms")
/////////////////////java/////////////////////////
        val nt2 = System.currentTimeMillis()
        for (i in 0..count) {
            val method = Test::class.java.getMethod("work")
            method.invoke(null)
        }
        val r2 = System.currentTimeMillis() - nt2
        println("--反射：java $r2 ms")
//------------------------缓存------------------------
        val nt5 = System.currentTimeMillis()
        val method = Test::class.java.getMethod("work")
        for (i in 0..count) {
            method.invoke(null)
        }
        val r5 = System.currentTimeMillis() - nt5
        println("--反射：java 带缓存 $r5 ms")
/////////////////////java/////////////////////////
        val nt3 = System.currentTimeMillis()
        for (i in 0..count) {
            Foo.javaClass.getMethod("foo").invoke(Foo)
        }
        val r3 = System.currentTimeMillis() - nt3
        println("--反射：kotlin $r3 ms")
//------------------------缓存------------------------
        val nt4 = System.currentTimeMillis()
        val m = Foo.javaClass.getMethod("foo")
        for (i in 0..count) {
            m.invoke(Foo)
        }
        val r4 = System.currentTimeMillis() - nt4
        println("--反射：kotlin 带缓存 $r4 ms")
    }
}

object Foo {
    fun foo(): Int {
        val i = 1
        return i + 1
    }
}