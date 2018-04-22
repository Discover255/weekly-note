# 复用类
要复用类，我们可以 __组合语法__ 和 __继承语法__ , 还有 __代理__

## 组合语法
组合语法十分简单，只需要创建一个新的类就可以了

## 继承语法
继承语法用到关键字 __extends__
继承的类会把方法和变量都继承下来

## 代理
有时候用组合和继承都不合适，这时可以用代理。

拿飞船举例子，已有一个“飞船控制”类，想复用它，实现飞船.前进，飞船.后退，就需要代理了。用继承也可以实现，但是不合逻辑

```Java
public class SpaceShip {
    private String name;
    private SpaceShipControls controls = new SpaceShipControls();
    public SpaceShip(String name) {
        this.name = name;
    }

    public void back() {
        controls.back();
    }
}
```

# 多态

书中举出了Instrument和导出类Wind的例子。这里引入了后期绑定这个概念，final能告诉编译器关闭后期绑定。

# 接口

以Instrument为例，Instrument是一个基类，它的方法往往是哑方法，直接使用会抛出错误。Instrument的目的是为了导出类创建一个 __通用接口__

Instrument是抽象基类，或简称抽象类

使用 __abstract__ 关键字可以使方法称为 __抽象方法__，含有抽象方法的类是抽象类

## 接口

__abstract__ 只是让某个方法成为抽象， __interface__ 这个关键字产生一个完全抽象的类。要让某个类遵循一个 __interface__ 要使用 __implements__ 关键字

抽象还有解耦都是为了复用

## 通过继承来扩展接口

多重继承如果有重复的方法，会非常复杂，它包含了覆盖、实现和重载，容易出错

## 适配接口

接口最常用的方法是 __策略设计模式__

## 接口中的域

接口中的域自动被置为static和final，它可以用来创建一个常量组，但是有了新的enum，就不需要这样用了