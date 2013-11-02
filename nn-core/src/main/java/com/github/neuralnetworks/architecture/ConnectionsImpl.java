package com.github.neuralnetworks.architecture;

import java.util.Set;

import com.github.neuralnetworks.util.UniqueList;

/**
 * this abstract class serves as a base for all weight matrices
 */
public abstract class ConnectionsImpl implements Connections, Comparable<ConnectionsImpl> {

    /**
     * input layer of neurons
     */
    protected Layer inputLayer;

    /**
     * output layer
     */
    protected Layer outputLayer;

    public ConnectionsImpl(Layer inputLayer, Layer outputLayer) {
	super();
	this.inputLayer = inputLayer;
	this.outputLayer = outputLayer;

	inputLayer.addConnection(this);
	outputLayer.addConnection(this);
    }

    @Override
    public Layer getInputLayer() {
	return inputLayer;
    }

    @Override
    public Layer getOutputLayer() {
	return outputLayer;
    }

    @Override
    public Set<Layer> getLayers() {
	Set<Layer> result = new UniqueList<Layer>();
	result.add(getInputLayer());
	result.add(getOutputLayer());
	return result;
    }

    @Override
    public Set<Connections> getConnections() {
	Set<Connections> result = new UniqueList<Connections>();
	result.add(this);
	return result;
    }

    @Override
    public int compareTo(ConnectionsImpl o) {
	return this.toString().compareTo(o.toString());
    }
}