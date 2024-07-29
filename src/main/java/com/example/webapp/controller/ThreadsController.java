package com.example.webapp.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.webapp.entity.Threads;
import com.example.webapp.entity.Titles;
import com.example.webapp.form.ThreadsForm;
import com.example.webapp.form.TitlesForm;
import com.example.webapp.helper.ThreadsHelper;
import com.example.webapp.helper.TitlesHelper;
import com.example.webapp.service.ThreadsService;
import com.example.webapp.service.TitlesService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/100-chan")
@RequiredArgsConstructor
public class ThreadsController {
	private final TitlesService titlesService;
	private final ThreadsService threadsService;

	/**
	 * タイトルをすべて表示する
	 * @param model
	 * @return threads/list.html
	 */
	@GetMapping
	public String list(Model model) {

		// スレッドの全タイトルをthreads/list.htmlに投げる
		model.addAttribute("titles", titlesService.findAllTitles());

		return "threads/list";
	}

	/**
	 * タイトルのidに対応した、スレッドを表示する
	 * @param form
	 * @param titleId
	 * @param model
	 * @param attributes
	 * @return threads/threads.html
	 */
	@GetMapping("/{titleId}")
	public String threads(@ModelAttribute ThreadsForm form, @PathVariable int titleId, Model model,
			RedirectAttributes attributes) {

		// リンク内の数字を使用してthreadをtitle_idで検索して、出力する
		model.addAttribute("title", titlesService.findByIdTitles(titleId));
		model.addAttribute("threads", threadsService.findByTitleThreads(titleId));

		return "threads/threads";
	}

	@GetMapping("/partial/{id}")
	public String getPartialContent(@ModelAttribute ThreadsForm form, @PathVariable Integer id, Model model) {

		model.addAttribute("threads", threadsService.findByTitleThreads(id));

		return "threads/threads_ajax";
	}

	/**
	 * スレッドを新規作成する
	 * @param threadsForm
	 * @param bindingResult
	 * @param titlesForm
	 * @param attributes
	 * @param userDetails
	 * @return redirect:/100-chan/TitleId
	 */
	@PostMapping("/save")
	public String addThreasd(@Validated ThreadsForm threadsForm, BindingResult bindingResult, TitlesForm titlesForm,
			RedirectAttributes attributes,
			@AuthenticationPrincipal UserDetails userDetails, Model model) {

		// ThreadsFormからThreadsに変換する
		Threads threads = ThreadsHelper.convertThreads(threadsForm);

		// threadがnullだったら、新しいthreadを追加しない
		if (bindingResult.hasErrors()) {
			return "redirect:/100-chan/" + threads.getTitleId();
		}

		// threadにログインしているユーザー名を保存する
		threads.setUsername(userDetails.getUsername());

		// 入力されたthread
		String thread = threads.getThread();

		// 入力されたthreadの改行コードを<br>タグにする
		threads.setThread(thread.replace("\n", "<br>"));

		// 新しいthreadを追加して、titleの更新時間を更新する
		threadsService.insert(threads);
		titlesService.updateTitle();

		return "redirect:/100-chan/" + threads.getTitleId();
	}

	/**
	 * タイトルを新規作成するページに遷移させる
	 * @param form
	 * @return threads/regist.html
	 */
	@GetMapping("/title-edit")
	public String newTitle(@ModelAttribute TitlesForm form) {
		return "threads/regist";
	}

	/**
	 * タイトルを新規作成する
	 * @param form
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("/regist")
	public String addTitle(@Validated TitlesForm form, BindingResult bindingResult) {

		// titleがnullだったらtitleを追加しない
		if (bindingResult.hasErrors()) {
			return "threads/regist";
		}

		// TitlesFormをTitlesに変換する
		Titles titles = TitlesHelper.convertTitles(form);

		// 新しいtitleを追加する
		titlesService.insertTilte(titles);

		return "redirect:/100-chan";
	}

	/**
	 * idに対応したスレッドを削除する
	 * 管理者(role = 'king')のみアクセス可能
	 * @param id
	 * @param form
	 * @return redirect:/100-chan/TitleId
	 */
	@PostMapping("/delete{id}/{titleId}")
	public String delete(@PathVariable("id") int id, @PathVariable("titleId") int titleId,
			@ModelAttribute ThreadsForm form) {

		// idに対応したthreadを削除する
		threadsService.delete(id);

		return "redirect:/100-chan/" + titleId;
	}

	/**
	 * idに対応したタイトルと、タイトルidに対応したスレッドを削除する
	 * 管理者(role = 'king')のみアクセス可能
	 * @param id
	 * @return
	 */
	@PostMapping("/deleteTitle{id}")
	public String titleDelete(@PathVariable int id) {

		// idに対応したtitleを削除する。（同時にthreadも削除される）
		titlesService.deleteTitle(id);

		return "redirect:/100-chan";
	}
}
