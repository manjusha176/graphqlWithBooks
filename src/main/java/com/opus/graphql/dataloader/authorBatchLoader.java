package com.opus.graphql.dataloader;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.dataloader.BatchLoader;

import com.opus.graphql.model.Author;

public class authorBatchLoader implements BatchLoader<Long, Author> {

	@Override
	public CompletionStage<List<Author>> load(List<Long> authorIds) {
//		return CompletableFuture.supplyAsync(() -> {
//            return userManager.loadUsersById(userIds);
//        });
		return null;
	}

}
