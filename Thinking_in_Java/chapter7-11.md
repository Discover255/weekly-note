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