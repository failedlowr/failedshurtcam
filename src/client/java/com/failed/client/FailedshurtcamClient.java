package com.failed.client;

import com.failed.config.Config;
import net.fabricmc.api.ClientModInitializer;

public class FailedshurtcamClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		Config.load();
	}
}