package com.scsx.test;

import java.io.IOException;
import java.util.Date;

import com.scsx.domain.Discussion;
import com.scsx.service.DiscussionService;

public class Main {

	public static void main(String[] args) throws IOException {
		Discussion discussion = new Discussion();
		discussion.setUNO(1);
		discussion.setPNO(2);
		discussion.setTIME(new Date());
		discussion.setDATA("你好。。。。。。。。。");
		DiscussionService.getDiscussionServiceInstance().insertDiscussion(discussion);
	}
}
