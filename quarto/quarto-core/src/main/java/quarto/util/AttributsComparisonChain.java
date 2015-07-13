package quarto.util;

import java.util.Comparator;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import com.google.common.primitives.Booleans;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;

/**
 * @author Steve Cancès
 * @since 1.0
 */
@FunctionalInterface
public interface AttributsComparisonChain<T> {

	static <T> AttributsComparisonChain<T> on(final T left, final T right) {
		final Tuple<T> tuple = Tuple.of(left, right);
		final ActiveAttributsComparisonChain<T> comparisonChain = () -> tuple;
		return comparisonChain;
	}

	default AttributsComparisonChain<T> compare(final Comparator<T> comparator) {
		return this;
	}

	default AttributsComparisonChain<T> compare(final ToIntFunction<T> projection) {
		return this;
	}

	default AttributsComparisonChain<T> compare(final ToLongFunction<T> projection) {
		return this;
	}

	default AttributsComparisonChain<T> compare(final ToFloatFunction<T> projection) {
		return this;
	}

	default AttributsComparisonChain<T> compare(final ToDoubleFunction<T> projection) {
		return this;
	}

	default AttributsComparisonChain<T> compareTrueFirst(final Predicate<T> projection) {
		return this;
	}

	default AttributsComparisonChain<T> compareFalseFirst(final Predicate<T> projection) {
		return this;
	}

	int result();

	@FunctionalInterface
	public interface ActiveAttributsComparisonChain<T> extends AttributsComparisonChain<T> {

		Tuple<T> tuple();

		@Override
		default AttributsComparisonChain<T> compare(final Comparator<T> comparator) {
			return this.classify(comparator.compare(
					this.tuple().left,
					this.tuple().right));
		}

		@Override
		default AttributsComparisonChain<T> compare(final ToIntFunction<T> projection) {
			return this.classify(Ints.compare(
					projection.applyAsInt(this.tuple().left),
					projection.applyAsInt(this.tuple().right)));
		}

		@Override
		default AttributsComparisonChain<T> compare(final ToLongFunction<T> projection) {
			return this.classify(Longs.compare(
					projection.applyAsLong(this.tuple().left),
					projection.applyAsLong(this.tuple().right)));
		}

		@Override
		default AttributsComparisonChain<T> compare(final ToFloatFunction<T> projection) {
			return this.classify(Float.compare(
					projection.applyAsFloat(this.tuple().left),
					projection.applyAsFloat(this.tuple().right)));
		}

		@Override
		default AttributsComparisonChain<T> compare(final ToDoubleFunction<T> projection) {
			return this.classify(Double.compare(
					projection.applyAsDouble(this.tuple().left),
					projection.applyAsDouble(this.tuple().right)));
		}

		@Override
		default AttributsComparisonChain<T> compareTrueFirst(final Predicate<T> projection) {
			// reversed
			return this.classify(Booleans.compare(
					projection.test(this.tuple().right),
					projection.test(this.tuple().left)));
		}

		@Override
		default AttributsComparisonChain<T> compareFalseFirst(final Predicate<T> projection) {
			return this.classify(Booleans.compare(
					projection.test(this.tuple().left),
					projection.test(this.tuple().right)));
		}

		@Override
		default int result() {
			return 0;
		}

		default AttributsComparisonChain<T> classify(final int result) {
			return (result < 0) ? () -> -1 : (result > 0) ? () -> 1 : this;
		}

	}

	public static class Tuple<T> {

		public final T left;

		public final T right;

		public Tuple(final T left, final T right) {
			this.left = left;
			this.right = right;
		}

		public static <T> Tuple<T> of(final T left, final T right) {
			return new Tuple<T>(left, right);
		}
	}

	public interface ToFloatFunction<T> {

		/**
		 * Applies this function to the given argument.
		 *
		 * @param value the function argument
		 * @return the function result
		 */
		float applyAsFloat(final T value);
	}
}
