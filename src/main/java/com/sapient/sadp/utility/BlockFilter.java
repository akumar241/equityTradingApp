package com.sapient.sadp.utility;

// TODO: Auto-generated Javadoc
/**
 * The Class Tuple3.represents a tuple of 3 values
 *
 * @param <First>
 *            the first generic type
 * @param <Second>
 *            the second generic type
 * @param <Third>
 *            the third generic type
 */
public class BlockFilter<First, Second, Third> {

	/** The first. */
	private First first;

	/** The second. */
	private Second second;

	/** The third. */
	private Third third;

	/**
	 * Instantiates a new tuple of three elements.
	 *
	 * @param first
	 *            the first element
	 * @param second
	 *            the second element
	 * @param third
	 *            the third element
	 */
	public BlockFilter(First first, Second second, Third third) {
		// null check
		if (first == null || second == null || third == null) {
			throw new NullPointerException();
		}
		this.first = first;
		this.second = second;
		this.third = third;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 *
	 *
	 * HashCode override
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + ((second == null) ? 0 : second.hashCode());
		result = prime * result + ((third == null) ? 0 : third.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	/*
	 * equals override
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@SuppressWarnings("rawtypes")
    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlockFilter other = (BlockFilter) obj;
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		if (second == null) {
			if (other.second != null)
				return false;
		} else if (!second.equals(other.second))
			return false;
		if (third == null) {
			if (other.third != null)
				return false;
		} else if (!third.equals(other.third))
			return false;
		return true;
	}
}
