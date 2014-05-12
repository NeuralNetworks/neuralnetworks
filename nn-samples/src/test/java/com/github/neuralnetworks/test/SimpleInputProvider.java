package com.github.neuralnetworks.test;

import com.github.neuralnetworks.calculation.neuronfunctions.TensorFunction;
import com.github.neuralnetworks.input.InputConverter;
import com.github.neuralnetworks.training.TrainingInputData;
import com.github.neuralnetworks.training.TrainingInputProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by linkerlin on 5/12/14.
 */
public class SimpleInputProvider implements TrainingInputProvider {
    float[][] X;
    float[][] Y;
    private Random random=new Random();
    /**
     * List of modifiers to apply on the input data after the conversion
     */
    private List<TensorFunction> inputModifiers;
    /**
     * Converter for the target
     */
    private InputConverter inputConverter;
    public SimpleInputProvider(){
        this(null);
    }
    public SimpleInputProvider(InputConverter inputConverter){
        super();
        this.inputConverter = inputConverter;
    }
    public SimpleInputProvider(float[][] X, float[][] Y){
        this(X,Y,null);
    }
    public SimpleInputProvider(float[][] X, float[][] Y,InputConverter inputConverter){
        super();
        this.X=X.clone();
        this.Y=Y.clone();
        this.inputConverter = inputConverter;
    }
    @Override
    public int getInputSize() {
        return X.length;
    }
    /**
     * Counter
     */
    protected int currentInput=0;
    protected int currentTarget=0;
    @Override
    public void reset() {
        this.currentInput=0;
        this.currentTarget=0;
    }

    @Override
    public float[] getNextInput() {
        return this.X[currentInput++];
    }

    @Override
    public float[] getNextTarget() {
        return this.Y[currentTarget++];
    }

    @Override
    public List<TensorFunction> getInputModifiers() {
        return inputModifiers;
    }

    public void addInputModifier(TensorFunction modifier) {
        if (inputModifiers == null) {
            inputModifiers = new ArrayList<>();
        }

        inputModifiers.add(modifier);
    }

    public void removeModifier(TensorFunction modifier) {
        if (inputModifiers != null) {
            inputModifiers.remove(modifier);
        }
    }

    @Override
    public void afterBatch(TrainingInputData ti) {
        reset();
    }

    @Override
    public void beforeBatch(TrainingInputData ti) {

    }

    @Override
    public void afterSample() {
        this.currentInput++;
        this.currentTarget++;
    }

    @Override
    public void beforeSample() {
        currentInput=random.nextInt(getInputSize());
        currentTarget=currentInput;
    }
    public InputConverter getInputConverter() {
        return inputConverter;
    }
    public void setInputConverter(InputConverter inputConverter) {
        this.inputConverter = inputConverter;
    }

//    @Override
//    public void populateNext(TrainingInputData ti) {
//
//    }
}
