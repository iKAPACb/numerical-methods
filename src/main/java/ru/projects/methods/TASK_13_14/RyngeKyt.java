package ru.projects.methods.TASK_13_14;

import java.util.ArrayList;

public class RyngeKyt extends Method {

    private ArrayList<Double> K1 = new ArrayList<>();
    private ArrayList<Double> K2 = new ArrayList<>();
    private ArrayList<Double> K3 = new ArrayList<>();
    private ArrayList<Double> K4 = new ArrayList<>();
    private ArrayList<Double> L1 = new ArrayList<>();
    private ArrayList<Double> L2 = new ArrayList<>();
    private ArrayList<Double> L3 = new ArrayList<>();
    private ArrayList<Double> L4 = new ArrayList<>();

    public RyngeKyt(double h) {
        super(h);
        initialization();
    }

    @Override
    protected void initialization() {
        for (int i = 0; i < x.size(); i++) {
            K1.add(h * fFunc(x.get(i), y.get(i), z.get(i)));
            L1.add(h * gFunc(x.get(i), y.get(i), z.get(i)));
            K2.add(h * fFunc(x.get(i) + 0.5 * h, y.get(i) + 0.5 * K1.get(i), z.get(i) + 0.5 * L1.get(i)));
            L2.add(h * gFunc(x.get(i) + 0.5 * h, y.get(i) + 0.5 * K1.get(i), z.get(i) + 0.5 * L1.get(i)));
            K3.add(h * fFunc(x.get(i) + 0.5 * h, y.get(i) + 0.5 * K2.get(i), z.get(i) + 0.5 * L2.get(i)));
            L3.add(h * gFunc(x.get(i) + 0.5 * h, y.get(i) + 0.5 * K2.get(i), z.get(i) + 0.5 * L2.get(i)));
            K4.add(h * fFunc(x.get(i) + h, y.get(i) + K3.get(i), z.get(i) + L3.get(i)));
            L4.add(h * gFunc(x.get(i) + h, y.get(i) + K3.get(i), z.get(i) + L3.get(i)));
            delta_y.add((K1.get(i) + 2 * K2.get(i) + 2 * K3.get(i) + K4.get(i)) / 6);
            delta_z.add((L1.get(i) + 2 * L2.get(i) + 2 * L3.get(i) + L4.get(i)) / 6);
            y.add(y.get(i) + delta_y.get(i));
            z.add(z.get(i) + delta_z.get(i));
        }
        delta_y.add(0.0);
        delta_z.add(0.0);
    }

    public ArrayList<RyngeKytBbIvod> createCells() {

        ArrayList<RyngeKytBbIvod> forTable = new ArrayList<>();

        for (int i = 0; i < x.size(); i++) {
            forTable.add(new RyngeKytBbIvod(i, h, x.get(i), y.get(i), z.get(i), delta_z.get(i), delta_y.get(i), istFunc(x.get(i)), Math.abs(istFunc(x.get(i)) - y.get(i))));
        }

        return forTable;
    }
}