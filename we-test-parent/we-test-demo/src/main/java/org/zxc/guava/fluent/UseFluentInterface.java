package org.zxc.guava.fluent;

import static com.google.common.base.Predicates.or;
import static org.zxc.guava.fluent.FluentIterable.with;

import java.util.Arrays;
import java.util.List;

import com.google.common.base.Predicate;

/**
 * 常规写法和平滑接口方式的区别
 * 1，常规看起来像这样，比如往列表里添加元素
 * private void makeNormal(Customer customer) {
        Order o1 = new Order();
        customer.addOrder(o1);
        OrderLine line1 = new OrderLine(6, Product.find("TAL"));
        o1.addLine(line1);
        OrderLine line2 = new OrderLine(5, Product.find("HPK"));
        o1.addLine(line2);
        OrderLine line3 = new OrderLine(3, Product.find("LGV"));
        o1.addLine(line3);
        line2.setSkippable(true);
        o1.setRush(true);
    }
 *
 *  2，平滑方式看起来像这样:
 *   private void makeFluent(Customer customer) {
        customer.newOrder()
                .with(6, "TAL")
                .with(5, "HPK").skippable()
                .with(3, "LGV")
                .priorityRush();
    }
    比如nutz的Cnd。
    类的方法返回实例本身即可达到这样的目的
 * 
 * @author 朱晓川
 *
 */
public class UseFluentInterface {
	
	public static void main(String[] args) {
		List<String> names = Arrays.asList("Stephan", "Chris", "Mike", "Miha", "Katrin");
		Iterable<String> filtered = 
		    with(names)
		   .filter(or(isEqualTo("Chris"), isEqualTo("Miha")))
		   .filter(lengthLessThan(5));
		
		for(String s : filtered){
			System.out.println(s); 
		}
	}
	
	private static class LengthLessThanPredicate implements Predicate<String> {
	    private final int length;
	    private LengthLessThanPredicate(final int length) {
	        this.length = length;
	    }
	    public boolean apply(final String s) {
	        return s.length() < length;
	    }
	}
	
	private static class EqualToPredicate implements Predicate<String> {
	    private final String value;
	    private EqualToPredicate(final String value) {
	        this.value = value;
	    }
		@Override
		public boolean apply(String input) {
			return value.equals(input); 
		}
	}
	
	public static Predicate<String> lengthLessThan(final int length) {
	    return new LengthLessThanPredicate(length);
	}
	
	public static Predicate<String> isEqualTo(final String value) {
	    return new EqualToPredicate(value);
	}
}
