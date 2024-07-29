package com.example.webapp.helper;

import com.example.webapp.entity.Threads;
import com.example.webapp.form.ThreadsForm;

public class ThreadsHelper {
	
	public static Threads convertThreads(ThreadsForm form) {
		Threads threads = new Threads();
		threads.setId(form.getId());
		threads.setTitleId(form.getTitleId());
		threads.setThread(form.getThread());
		threads.setUsername(form.getUsername());
		threads.setCreatedAt(form.getCreatedAt());
		return threads;
	}
	
	public static ThreadsForm convertThreadsForm(Threads threads) {
		ThreadsForm form = new ThreadsForm();
		form.setId(threads.getId());
		form.setTitleId(threads.getTitleId());
		form.setThread(threads.getThread());
		form.setUsername(threads.getUsername());
		form.setCreatedAt(threads.getCreatedAt());
		return form;
	}
}
