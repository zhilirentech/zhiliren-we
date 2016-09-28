package org.zxc.guava;

import com.google.common.net.InternetDomainName;

public class Test {

	public static void main(String[] args) {
		InternetDomainName owner = InternetDomainName.from("test.train.xiaoka360.com").topPrivateDomain();
		System.out.println(owner.toString());     

	}

}
