package sml;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * An object that contains all the labels associated with the .sml file that is being read
 * @author Krysten Lawrence
 */
public final class Labels {
	private final Map<String, Integer> labels = new HashMap<>();

	/**
	 * Adds a label with the associated address to the map.
	 *
	 * @param label the label
	 * @param address the address the label refers to
	 */
	public void addLabel(String label, int address){
		Objects.requireNonNull(label);
		if(!labels.containsKey(label)){
			labels.put(label, address);
		}
		else{
			throw new IllegalArgumentException("Label already exists, please choose another value");
		}
	}

	/**
	 * Returns the address associated with the label.
	 *
	 * @param label the label
	 * @return the address the label refers to
	 */
	public int getAddress(String label) {
		// TODO: Where can NullPointerException be thrown here?
		//       A NullPointerException can be thrown if a search is done on the labels map and
		//       no key named for the given value is found
		if(!labels.containsKey(label)){
			throw new NullPointerException("The label does not exist");
		}
		return labels.get(label);
	}

	/**
	 * representation of this instance,
	 * in the form "[label -> address, label -> address, ..., label -> address]"
	 *
	 * @return the string representation of the labels map
	 */
	@Override
	public String toString() {
		return labels.entrySet().stream()
				.sorted(Map.Entry.comparingByKey())
				.map(e -> e.getKey() + " -> " + e.getValue())
				.collect(Collectors.joining(", ", "[", "]")) ;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Labels l) {
			return this.labels.equals(l.labels);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return labels.hashCode();
	}

	/**
	 * Removes the labels
	 */
	public void reset() {
		labels.clear();
	}
}
