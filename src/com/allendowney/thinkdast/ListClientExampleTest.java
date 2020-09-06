package com.allendowney.thinkdast;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import com.allendowney.thinkdast.ListClientExample;

import org.junit.Test;

public class ListClientExampleTest {
	// junit을 위한 코드
	
	// @test를 선언한 메소드는 JUnit이 알아서 실행을 해준다.
	@Test
	public void testListClientExample() {
		ListClientExample lce = new ListClientExample();
		@SuppressWarnings("rawtypes")
		List list = lce.getList();
		// assertThat은 두개의 파라미터값을 줘서 비교한다.
		// 첫번째 파라미터는 비교대상 실제값
		// 두번째 파라미터는 비교로직이 담긴 Matcher가 사용되게 된다.
		assertThat(list, instanceOf(ArrayList.class));
		
		//JUnit 기본 제공 매쳐
		/*
	   *- allOf
			내부에 선언된 모든 매쳐가 정상일 경우 통과한다.
			assertThat("myValue", allOf(startsWith("my"), containsString("Val”)))
			
		- anyOf
			내부에 선언된 매쳐중 하나 이상 통과할 경우 통과한다.
			assertThat("myValue", anyOf(startsWith("foo"), containsString("Val”)))
			
		- both
			both A and B 형식으로 matcher를 사용할 수 있게 해 준다.
			A, B 매쳐 둘다 통과할 경우 테스트가 성공한다.
			assertThat("fab", both(containsString("a")).and(containsString(“b”)))
			
		- either
			either A or B 형식으로 matcher를 사용할 수 있게 해 준다.
			A, B 매쳐 둘중 하나가 성공할 경우 테스트가 성공한다.
			assertThat("fan", either(containsString(“a”)).or(containsString(“b”)))
			
		- describedAs
			매쳐내부의 메시지를 변경할 수 있다.
			assertThat (new BigDecimal(“32123”), describedAs("a big decimal equal to %0", equalTo(myBigDecimal), myBigDecimal.toPlainString()));
			everyItem
			배열이나 리스트를 순회하며 매쳐가 실행된다.
			assertThat(Arrays.asList("bar", "baz"), everyItem(startsWith("ba”)));
			
		- is
			is는 두가지 용도로 사용할 수 있다.
			A is B와 같이 비교값이 서로 같은지 여부를 확인할 경우
			assertThat("Simple Text", is("Simple Text"));
			이경우 assertThat("Simple Text", is(equalTo("Simple Text")))와 동일하게 사용할 수 있다.
			다른 매쳐를 꾸며주는 용도로 사용. 매쳐에는 영향을 끼치지 않으며, 조금 더 표현력이 있도록 변경하여 준다.
			assertThat("Simple Text", is(not("simpleText")));
			위의 경우 is가 빠지더라도 문제없이 작동된다. 하지만 is가 있음으로써 쉽게 읽혀지는 테스트 코드가 된다.
			
		- isA
			비교되는 값이 특정 클래스일 경우 테스트가 통과된다. is(instanceOf(SomeClass.class))와 동일하다.
			assertThat(cheese, isA(Cheddar.class))
			
		- anything
			항상 true를 반환하는 매쳐
			
		- hasItem
			배열에서 매쳐가 통과하는 값이 하나 이상이 있는지 여부를 검사한다.
			assertThat(Arrays.asList("foo", "bar"), hasItem("bar"))
			
		- hasItems
			배열에서 매쳐리스트에 선언된 값들 모두가 하나 이상 있는지 여부를 검사한다.
			assertThat(Arrays.asList("foo", "bar", "baz"), hasItems("baz", "foo"))
			
		- equalTo
			두 값이 같은지 여부를 체크한다. is와 동일하게 사용할 수 있다.
			assertThat("foo", equalTo("foo"));
			
		- any
			비교값이 매쳐의 타입과 동일한지 여부를 체크한다. instanceOf와는 다르게 매쳐의 값은 앞서 비교값의 타입의 자식만 비교할 수 있다.
			assertThat(new Canoe(), instanceOf(Canoe.class));
			
		- instanceOf
			비교값이 매쳐의 타입과 동일한지 여부를 체크한다. any와는 다르게 매쳐의 값은 비교값과 연관없는 경우에도 사용할 수 있다.
			assertThat(new Canoe(), instanceOf(Paddlable.class));
			
		- not
			is와 동일하게 두가지 경우로 사용할 수 있다.
			내부에 매쳐를 선언할 경우 내부 매쳐의결과를 뒤집는다.
			assertThat(cheese, is(not(equalTo(smelly))))
			not뒤에 값이 나올 경우, 같지 않을 경우 테스트가 통과한다.
			assertThat("Test", not("tEST"));
			
		- nullValue
			비교값이 null일경우 테스트가 통과한다.
			assertThat(cheese, is(nullValue())
			
		- notNullValue
			비교값이 null이 아닐경우 테스트가 통과한다.
			assertThat(cheese, is(notNullValue()))
			
		- sameInstance
			비교매쳐의 값과 같은 인스턴스일 경우 테스트가 통과한다. theInstance 와 동일
			assertThat("Test", not(sameInstance("not Same Instance")));
			
		- theInstance
			비교매쳐의 값과 같은 인스턴스일 경우 테스트가 통과한다. sameInstance 와 동일
			assertThat("Test", not(sameInstance("not Same Instance")));
			
		- containsString
			특정 문자열이 있는지를 검사한다.
			assertThat("myStringOfNote", containsString("ring"));
			
		- startsWith
			특정 문자열로 시작하는지를 검사한다.
			assertThat("myStringOfNote", startsWith("my"))
			
		- endsWith
			특정 문자열로 종료되는지를 검사한다.
			assertThat("myStringOfNote", endsWith("Note"))
			
			
			*/
		
	}
}
