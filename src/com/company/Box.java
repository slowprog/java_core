package com.company;

public class Box {
    int weight;

    public Box(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Box) {
            Box box = (Box)obj;

            return this.weight == box.weight;
        }

        return super.equals(obj);
    }

    @Override
    public int hashCode() { // если два объекта равны в equals, то и hashCode должны быть равны
        return this.weight * 2;
        // return super.hashCode();
    }

    @Override
    public String toString() {
        return "Box: " + this.weight;
    }
}
