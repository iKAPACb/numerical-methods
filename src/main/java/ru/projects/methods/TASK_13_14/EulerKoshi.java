package ru.projects.methods.TASK_13_14;

import java.util.ArrayList;

public class EulerKoshi extends Method {

    private ArrayList<Double> yr = new ArrayList<>();
    private ArrayList<Double> zr = new ArrayList<>();

    public EulerKoshi(double h) {
        super(h);
        yr.add(2.0);
        zr.add(2.0);
        delta_y.add(0.0);
        delta_z.add(0.0);
        initialization();
    }

    @Override
    protected void initialization() {
        for (int i = 0; i < x.size() - 1; i++) {
            zr.add(z.get(i) + h * gFunc(x.get(i), y.get(i), z.get(i)));
            yr.add(y.get(i) + h * fFunc(x.get(i), y.get(i), z.get(i)));
            z.add(z.get(i) + (h / 2) * (gFunc(x.get(i), y.get(i), z.get(i)) + gFunc(x.get(i + 1), yr.get(i), zr.get(i + 1))));
            y.add(y.get(i) + (h / 2) * (fFunc(x.get(i), y.get(i), z.get(i)) + fFunc(x.get(i + 1), yr.get(i), zr.get(i + 1))));
            delta_z.add((h / 2) * (gFunc(x.get(i), y.get(i), z.get(i)) + gFunc(x.get(i + 1), yr.get(i), zr.get(i + 1))));
            delta_y.add((h / 2) * (fFunc(x.get(i), y.get(i), z.get(i)) + fFunc(x.get(i + 1), yr.get(i), zr.get(i + 1))));
        }
    }

    public ArrayList<EulerKoshiBbIvod> createCells() {

        ArrayList<EulerKoshiBbIvod> forTable = new ArrayList<>();

        for (int i = 0; i <= x.size() - 1; i++) {
            forTable.add(new EulerKoshiBbIvod(i, h, x.get(i), y.get(i), z.get(i),yr.get(i),zr.get(i),delta_z.get(i), delta_y.get(i), istFunc(x.get(i)), Math.abs(istFunc(x.get(i)) - y.get(i))));
        }

        return forTable;
    }
}