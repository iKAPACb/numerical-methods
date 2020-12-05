package ru.projects.methods.TASK_11_12.integrals;


public class IntegralSolver {

    private IntegralRange integralRange;

    public IntegralSolver(IntegralRange integralRange) {
        this.integralRange = integralRange;
    }

    public double methodLeftRectangles (){
        double sum = 0;
        for (double i = integralRange.getX0(); i <= integralRange.getXK(); i += integralRange.getH())
        sum += integralRange.getH() * IntegralUtil.func(i);
        System.out.println(sum);
        return sum;
    }

    public double methodRightRectangles (){
        double sum = 0;
        for (double i = integralRange.getX0(); i <= integralRange.getXK(); i += integralRange.getH()) {
            sum += integralRange.getH() * IntegralUtil.func(i);
        }
        return sum;
    }
    //
    public double methodMiddleRectangles (){
        double sum = 0;
        for (double i = integralRange.getX0(); i < integralRange.getXK(); i += integralRange.getH()) {
            sum += integralRange.getH() * IntegralUtil.func((i + i + integralRange.getH()) / 2);
        }
        return sum;
    };

    public double methodTrapeze (){
        double sum = 0;
        for (double i = integralRange.getX0(); i <= integralRange.getXK(); i += integralRange.getH()) {
            if (i == integralRange.getX0()) {
                sum += integralRange.getH() * IntegralUtil.func(i) / 2;
            } else if (i == integralRange.getXK()) {
                sum += integralRange.getH() * IntegralUtil.func(i) / 2;
            } else {
                sum += integralRange.getH() * IntegralUtil.func(i);
            }
        }
        return sum;
    }

    public double methodSimpson (){
        double sum = 0;
        for (double i = integralRange.getX0(), k = 0; i <= integralRange.getXK(); i += integralRange.getH(), k++) {
            if (i == integralRange.getX0() || i == integralRange.getXK()) {
                sum += (integralRange.getH() / 3) * IntegralUtil.func(i);
            } else {
                sum += k % 2 == 1 ? 4 * (integralRange.getH() / 3) * IntegralUtil.func(i) : 2 * (integralRange.getH() / 3) * IntegralUtil.func(i);
            }
        }
        return sum;
    };
}
