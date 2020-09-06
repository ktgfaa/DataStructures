package com.allendowney.thinkdast;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import com.allendowney.thinkdast.ListClientExample;

import org.junit.Test;

public class ListClientExampleTest {
	// junit�� ���� �ڵ�
	
	// @test�� ������ �޼ҵ�� JUnit�� �˾Ƽ� ������ ���ش�.
	@Test
	public void testListClientExample() {
		ListClientExample lce = new ListClientExample();
		@SuppressWarnings("rawtypes")
		List list = lce.getList();
		// assertThat�� �ΰ��� �Ķ���Ͱ��� �༭ ���Ѵ�.
		// ù��° �Ķ���ʹ� �񱳴�� ������
		// �ι�° �Ķ���ʹ� �񱳷����� ��� Matcher�� ���ǰ� �ȴ�.
		assertThat(list, instanceOf(ArrayList.class));
		
		//JUnit �⺻ ���� ����
		/*
	   *- allOf
			���ο� ����� ��� ���İ� ������ ��� ����Ѵ�.
			assertThat("myValue", allOf(startsWith("my"), containsString("Val��)))
			
		- anyOf
			���ο� ����� ������ �ϳ� �̻� ����� ��� ����Ѵ�.
			assertThat("myValue", anyOf(startsWith("foo"), containsString("Val��)))
			
		- both
			both A and B �������� matcher�� ����� �� �ְ� �� �ش�.
			A, B ���� �Ѵ� ����� ��� �׽�Ʈ�� �����Ѵ�.
			assertThat("fab", both(containsString("a")).and(containsString(��b��)))
			
		- either
			either A or B �������� matcher�� ����� �� �ְ� �� �ش�.
			A, B ���� ���� �ϳ��� ������ ��� �׽�Ʈ�� �����Ѵ�.
			assertThat("fan", either(containsString(��a��)).or(containsString(��b��)))
			
		- describedAs
			���ĳ����� �޽����� ������ �� �ִ�.
			assertThat (new BigDecimal(��32123��), describedAs("a big decimal equal to %0", equalTo(myBigDecimal), myBigDecimal.toPlainString()));
			everyItem
			�迭�̳� ����Ʈ�� ��ȸ�ϸ� ���İ� ����ȴ�.
			assertThat(Arrays.asList("bar", "baz"), everyItem(startsWith("ba��)));
			
		- is
			is�� �ΰ��� �뵵�� ����� �� �ִ�.
			A is B�� ���� �񱳰��� ���� ������ ���θ� Ȯ���� ���
			assertThat("Simple Text", is("Simple Text"));
			�̰�� assertThat("Simple Text", is(equalTo("Simple Text")))�� �����ϰ� ����� �� �ִ�.
			�ٸ� ���ĸ� �ٸ��ִ� �뵵�� ���. ���Ŀ��� ������ ��ġ�� ������, ���� �� ǥ������ �ֵ��� �����Ͽ� �ش�.
			assertThat("Simple Text", is(not("simpleText")));
			���� ��� is�� �������� �������� �۵��ȴ�. ������ is�� �������ν� ���� �������� �׽�Ʈ �ڵ尡 �ȴ�.
			
		- isA
			�񱳵Ǵ� ���� Ư�� Ŭ������ ��� �׽�Ʈ�� ����ȴ�. is(instanceOf(SomeClass.class))�� �����ϴ�.
			assertThat(cheese, isA(Cheddar.class))
			
		- anything
			�׻� true�� ��ȯ�ϴ� ����
			
		- hasItem
			�迭���� ���İ� ����ϴ� ���� �ϳ� �̻��� �ִ��� ���θ� �˻��Ѵ�.
			assertThat(Arrays.asList("foo", "bar"), hasItem("bar"))
			
		- hasItems
			�迭���� ���ĸ���Ʈ�� ����� ���� ��ΰ� �ϳ� �̻� �ִ��� ���θ� �˻��Ѵ�.
			assertThat(Arrays.asList("foo", "bar", "baz"), hasItems("baz", "foo"))
			
		- equalTo
			�� ���� ������ ���θ� üũ�Ѵ�. is�� �����ϰ� ����� �� �ִ�.
			assertThat("foo", equalTo("foo"));
			
		- any
			�񱳰��� ������ Ÿ�԰� �������� ���θ� üũ�Ѵ�. instanceOf�ʹ� �ٸ��� ������ ���� �ռ� �񱳰��� Ÿ���� �ڽĸ� ���� �� �ִ�.
			assertThat(new Canoe(), instanceOf(Canoe.class));
			
		- instanceOf
			�񱳰��� ������ Ÿ�԰� �������� ���θ� üũ�Ѵ�. any�ʹ� �ٸ��� ������ ���� �񱳰��� �������� ��쿡�� ����� �� �ִ�.
			assertThat(new Canoe(), instanceOf(Paddlable.class));
			
		- not
			is�� �����ϰ� �ΰ��� ���� ����� �� �ִ�.
			���ο� ���ĸ� ������ ��� ���� �����ǰ���� �����´�.
			assertThat(cheese, is(not(equalTo(smelly))))
			not�ڿ� ���� ���� ���, ���� ���� ��� �׽�Ʈ�� ����Ѵ�.
			assertThat("Test", not("tEST"));
			
		- nullValue
			�񱳰��� null�ϰ�� �׽�Ʈ�� ����Ѵ�.
			assertThat(cheese, is(nullValue())
			
		- notNullValue
			�񱳰��� null�� �ƴҰ�� �׽�Ʈ�� ����Ѵ�.
			assertThat(cheese, is(notNullValue()))
			
		- sameInstance
			�񱳸����� ���� ���� �ν��Ͻ��� ��� �׽�Ʈ�� ����Ѵ�. theInstance �� ����
			assertThat("Test", not(sameInstance("not Same Instance")));
			
		- theInstance
			�񱳸����� ���� ���� �ν��Ͻ��� ��� �׽�Ʈ�� ����Ѵ�. sameInstance �� ����
			assertThat("Test", not(sameInstance("not Same Instance")));
			
		- containsString
			Ư�� ���ڿ��� �ִ����� �˻��Ѵ�.
			assertThat("myStringOfNote", containsString("ring"));
			
		- startsWith
			Ư�� ���ڿ��� �����ϴ����� �˻��Ѵ�.
			assertThat("myStringOfNote", startsWith("my"))
			
		- endsWith
			Ư�� ���ڿ��� ����Ǵ����� �˻��Ѵ�.
			assertThat("myStringOfNote", endsWith("Note"))
			
			
			*/
		
	}
}
