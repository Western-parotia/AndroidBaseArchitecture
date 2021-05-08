# 1.指定混淆时采用的算法，后面的参数是一个过滤器
# 这个过滤器是谷歌推荐的算法，一般不改变
#-optimizations !code/simplification/artithmetic,!field/*,!class/merging/*

# 2.代码混淆压缩比，在0~7之间，默认为5,一般不需要修改
#-optimizationpasses 5
-dontshrink