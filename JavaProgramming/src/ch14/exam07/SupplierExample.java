package ch14.exam07;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.ObjIntConsumer;

public class SupplierExample {
	public static void main(String[] args) {
		method1( s -> System.out.println("°ú¸ñ: " + s) );
		method2( (s1, s2) ->  System.out.println(s1 + "-" + s2) );
		method3( (o, i) -> {
			for(int k=0; k<i; k++) {
				System.out.println(o);
			}
		} );
	}

	public static void method1(Consumer<String> arg) {
		arg.accept("Java");
	}
	
	public static void method2(BiConsumer<String, String> arg) {
		arg.accept("Iot", "Java");
	}
	
	public static void method3(ObjIntConsumer<String> arg) {
		arg.accept("È«±æµ¿", 3);
	}
}
