package ta.stc_decisionTree.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import ta.stc_decisionTree.model.ClassNode;
import ta.stc_decisionTree.model.DecisionNode;
import ta.stc_decisionTree.model.DecisionTree;
import ta.stc_decisionTree.model.Node;
import ta.stc_decisionTree.model.TabelTree;

public class AlgoritmaID3{
    // the list of attributes
    private String[] allAttributes; 
    // the position of the target attribute in the list of attributes
    private int indexTargetAttribute = -1; 
    // the set of values for the target attribute
    private Set<String> targetAttributeValues = new HashSet<String>(); 

    // for statistics
    private long startTime; // start time of the latest execution
    private long endTime;   // end time of the latest execution

    /**
     * Create a decision tree from a set of training instances.
     * @param tabelTree  tabel tree decision tree
     * @return pohon decision tree
     */
    public DecisionTree runAlgorithm(TabelTree tabelTree) {
        // record the start time
        startTime = System.currentTimeMillis();

        // create an empty decision tree
        DecisionTree tree = new DecisionTree();
        tree.tabelTree = tabelTree;
        
        allAttributes = tabelTree.getHeaderTabel();
        int[] remainingAttributes = new int[allAttributes.length - 1];
        int pos = 0;
        for(int i=0;i<allAttributes.length;i++){
            if(allAttributes[i].equals(tabelTree.getTarget())){
                indexTargetAttribute = i;
            }else{
                remainingAttributes[pos++] = i;
            }
        }
        List<String[]> instances = tabelTree.getBodyTabel();
        for(String[] row : instances){
            targetAttributeValues.add(row[indexTargetAttribute]);
        }

        tree.root = id3(remainingAttributes, instances);
        tree.allAttributes = allAttributes;

        endTime = System.currentTimeMillis();  // record end time
        return tree; // return the tree
        
    }

    /**
     * Method to create a subtree according to a set of attributes and training
     * instances.
     * @param remainingAttributes remaining attributes to create the tree
     * @param instances a list of training instances
     * @return node of the subtree created
     */
    private Node id3(int[] remainingAttributes, List<String[]> instances) {
        // if only one remaining attribute,
        // return a class node with the most common value in the instances
        if (remainingAttributes.length == 0) {
            // Count the frequency of class
            Map<String, Integer> targetValuesFrequency = 
                    calculateFrequencyOfAttributeValues(instances, indexTargetAttribute);

            // Loop over the values to find the class with the highest frequency
            int highestCount = 0;
            String highestName = "";

            for (Map.Entry<String, Integer> entry : targetValuesFrequency.entrySet()) {
                    // if the frequency is higher
                    if (entry.getValue() > highestCount) {
                        highestCount = entry.getValue();
                        highestName = entry.getKey();
                    }
            }
            // return a class node with the value having the highest frequency
            ClassNode classNode = new ClassNode();
            classNode.className = highestName;
            return classNode;
        }

        // Calculate the frequency of each target attribute value and
        // at the same time check if there is a single class.
        Map<String, Integer> targetValuesFrequency = calculateFrequencyOfAttributeValues(
                        instances, indexTargetAttribute);

        // if all instances are from the same class
        if (targetValuesFrequency.entrySet().size() == 1) {
            ClassNode classNode = new ClassNode();
            classNode.className = (String) targetValuesFrequency.keySet().toArray()[0];
            return classNode;
        }

        // Calculate global entropy
        double globalEntropy = 0d;
        // for each value
        for (String value : targetAttributeValues) {
            // calculate frequency
            double frequency = targetValuesFrequency.get(value)/ (double) instances.size();

            // update the global entropy
            globalEntropy -= frequency * Math.log(frequency) / Math.log(2);
        }
        // System.out.println("Global entropy = " + globalEntropy);

        // Select the attribute from remaining attributes such that if we split
        // the dataset on this
        // attribute, we will get the higher information gain
        int attributeWithHighestGain = 0;
        double highestGain = -99999;
        for (int attribute : remainingAttributes) {
            double gain = calculateGain(attribute, instances, globalEntropy);
            // System.out.println("Process " + allAttributes[attribute] +
            // " gain = " + gain);
            if (gain >= highestGain) {//mengambil gain tertinggi
                highestGain = gain;
                attributeWithHighestGain = attribute;
            }
        }

        // Create a decision node for the attribute
        // System.out.println("Attribute with highest gain = " +
        // allAttributes[attributeWithHighestGain] + " " + highestGain);
        DecisionNode decisionNode = new DecisionNode();
        decisionNode.attribute = attributeWithHighestGain;

        // calculate the list of remaining attribute after we remove the
        // attribute
        int[] newRemainingAttribute = new int[remainingAttributes.length - 1];
        int pos = 0;
        for (int i = 0; i < remainingAttributes.length; i++) {
                if (remainingAttributes[i] != attributeWithHighestGain) {
                        newRemainingAttribute[pos++] = remainingAttributes[i];
                }
        }

        // Split the dataset into partitions according to the selected attribute
        Map<String, List<String[]>> partitions = new HashMap<String, List<String[]>>();
        for (String[] instance : instances) {
            String value = instance[attributeWithHighestGain];
            List<String[]> listInstances = partitions.get(value);
            if (listInstances == null) {
                    listInstances = new ArrayList<String[]>();
                    partitions.put(value, listInstances);
            }
            listInstances.add(instance);
        }

        // Create the values for the subnodes
        decisionNode.nodes = new Node[partitions.size()];
        decisionNode.attributeValues = new String[partitions.size()];

        // For each partition, make a recursive call to create
        // the corresponding branches in the tree.
        int index = 0;
        for (Map.Entry<String, List<String[]>> partition : partitions.entrySet()) {
            decisionNode.attributeValues[index] = partition.getKey();
            decisionNode.nodes[index] = id3(newRemainingAttribute,
                            partition.getValue()); // recursive call
            index++;
        }

        // return the root node of the subtree created
        return decisionNode;
    }

    /**
     * Calculate the information gain of an attribute for a set of instance
     * @param attributePos the position of the attribute
     * @param instances a list of instances
     * @param globalEntropy the global entropy
     * @return the gain
     */
    private double calculateGain(int attributePos, List<String[]> instances,
                double globalEntropy) {
        // Count the frequency of each value for the attribute
        Map<String, Integer> valuesFrequency = calculateFrequencyOfAttributeValues(
                        instances, attributePos);

        // Calculate the gain
        double sum = 0;
        // for each value
        for (Map.Entry<String, Integer> entry : valuesFrequency.entrySet()) {
            // make the sum 
            sum += entry.getValue()
                            / ((double) instances.size())
                            * calculateEntropyIfValue(instances, attributePos,
                                            entry.getKey());
        }
        // subtract the sum from the global entropy
        return globalEntropy - sum;
    }

    /**
     * Calculate the entropy for the target attribute, if a given attribute has
     * a given value.
     * 
     * @param instances
     *            : list of instances
     * @param attributeIF
     *            : the given attribute
     * @param valueIF
     *            : the given value
     * @return entropy
     */
    private double calculateEntropyIfValue(List<String[]> instances,
                int attributeIF, String valueIF) {

        // variable to count the number of instance having the value for that
        // attribute
        int instancesCount = 0;

        // variable to count the frequency of each value
        Map<String, Integer> valuesFrequency = new HashMap<String, Integer>();

        // for each instance
        for (String[] instance : instances) {
            // if that instance has the value for the attribute
            if (instance[attributeIF].equals(valueIF)) {
                String targetValue = instance[indexTargetAttribute];
                // increase the frequency
                if (valuesFrequency.get(targetValue) == null) {
                    valuesFrequency.put(targetValue, 1);
                } else {
                    valuesFrequency.put(targetValue,
                                    valuesFrequency.get(targetValue) + 1);
                }
                // increase the number of instance having the value for that
                // attribute
                instancesCount++; 
            }
        }
        // calculate entropy
        double entropy = 0;
        // for each value of the target attribute
        for (String value : targetAttributeValues) {
            // get the frequency
            Integer count = valuesFrequency.get(value);
            // if the frequency is not null
            if (count != null) {
                // update entropy according to the formula
                double frequency = count / (double) instancesCount;
                entropy -= frequency * Math.log(frequency) / Math.log(2);
            }
        }
        return entropy;
    }

    /**
     * This method calculates the frequency of each value for an attribute in a
     * given set of instances
     * 
     * @param instances
     *            A set of instances
     * @param indexAttribute
     *            The attribute.
     * @return A map where the keys are attributes and values are the number of
     *         times that the value appeared in the set of instances.
     */
    private Map<String, Integer> calculateFrequencyOfAttributeValues(
                List<String[]> instances, int indexAttribute) {
        // A map to calculate the frequency of each value:
        // Key: a string indicating a value
        // Value:  the frequency
        Map<String, Integer> targetValuesFrequency = new HashMap<String, Integer>();

        // for each instance of the training set
        for (String[] instance : instances) {
            // get the value of the attribute for that instance
            String targetValue = instance[indexAttribute];
            // increase the frequency by 1
            if (targetValuesFrequency.get(targetValue) == null) {
                targetValuesFrequency.put(targetValue, 1); //no,1
            } else {
                targetValuesFrequency.put(targetValue, targetValuesFrequency.get(targetValue) + 1);
            }
        }
        // return the map
        return targetValuesFrequency;
    }//hitung berapa yang play dan berapa yang no dan yes

    /**
     * Print statistics about the execution of this algorithm
     */
    public void printStatistics() {
        System.out.println("Time to construct decision tree = "
                        + (endTime - startTime) + " ms");
        System.out.println("Target attribute = "
                        + allAttributes[indexTargetAttribute]);
        System.out.print("Other attributes = ");
        for (String attribute : allAttributes) {
            if (!attribute.equals(allAttributes[indexTargetAttribute])) {
                System.out.print(attribute + " ");
            }
        }
        System.out.println();
    }
}
