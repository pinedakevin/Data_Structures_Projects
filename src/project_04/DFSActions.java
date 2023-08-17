package project_04;

/**
 * Interface that provides a skeleton for actions needed during a depth first
 * search.
 * 
 * @author Kevin Pineda
 * @class CMSC 350-6381
 * @Project 4
 * @Date 07/03/2023
 * @param <T>
 */
public interface DFSActions<T> {
	void cycleDetected();

	void processVertex(T vertex);

	void descend(T vertex);

	void ascend(T vertex);
}
