package br.com.pereiraeng.core.collections.comparison;

import java.util.Comparator;

/**
 * Classe do objeto que compara vetores
 * 
 * @author Philipe PEREIRA
 *
 */
public abstract class ArrayComparator<K> implements Comparator<K> {

	private int index;

	private boolean asc;

	private ArrayComparator() {
		this(0);
	}

	private ArrayComparator(int index) {
		this(index, true);
	}

	private ArrayComparator(int index, boolean asc) {
		this.index = index;
		this.asc = asc;
	}

	public static class Integer extends ArrayComparator<int[]> {

		public Integer() {
			super();
		}

		public Integer(int index) {
			super(index);
		}

		public Integer(int index, boolean asc) {
			super(index, asc);
		}

		@Override
		public int compare(int[] o1, int[] o2) {
			int s1, s2;
			if (super.index == -1) {
				s1 = 0;
				for (int i = 0; i < o1.length; i++)
					s1 += o1[i];
				s2 = 0;
				for (int i = 0; i < o2.length; i++)
					s2 += o2[i];
			} else {
				s1 = o1[super.index];
				s2 = o2[super.index];
			}
			if (super.asc)
				return java.lang.Integer.compare(s1, s2);
			else
				return java.lang.Integer.compare(s2, s1);
		}
	}

	public static class Float extends ArrayComparator<float[]> {

		public Float() {
			super();
		}

		public Float(int index) {
			super(index);
		}

		public Float(int index, boolean asc) {
			super(index, asc);
		}

		@Override
		public int compare(float[] o1, float[] o2) {
			float s1, s2;
			if (super.index == -1) {
				s1 = 0f;
				for (int i = 0; i < o1.length; i++)
					s1 += o1[i];
				s2 = 0f;
				for (int i = 0; i < o2.length; i++)
					s2 += o2[i];
			} else {
				s1 = o1[super.index];
				s2 = o2[super.index];
			}
			if (super.asc)
				return java.lang.Float.compare(s1, s2);
			else
				return java.lang.Float.compare(s2, s1);
		}
	}

	public static class Object extends ArrayComparator<java.lang.Object[]> {

		public Object() {
			super();
		}

		public Object(int index) {
			super(index);
		}

		public Object(int index, boolean asc) {
			super(index, asc);
		}

		@Override
		public int compare(java.lang.Object[] o0, java.lang.Object[] o1) {
			java.lang.Object obj0 = null, obj1 = null;
			if (super.asc) {
				obj0 = o0[super.index];
				obj1 = o1[super.index];
			} else {
				obj0 = o1[super.index];
				obj1 = o0[super.index];
			}

			if (obj0 instanceof Number && obj1 instanceof Number)
				return ((java.lang.Integer) obj0).compareTo(((java.lang.Integer) obj1));
			else if (obj0 instanceof Float && obj1 instanceof Float)
				return ((java.lang.Float) obj0).compareTo(((java.lang.Float) obj1));
			else if (obj0 instanceof Double && obj1 instanceof Double)
				return ((java.lang.Double) obj0).compareTo(((java.lang.Double) obj1));
			else
				return obj0.toString().compareTo(obj1.toString());
		}
	}
}
