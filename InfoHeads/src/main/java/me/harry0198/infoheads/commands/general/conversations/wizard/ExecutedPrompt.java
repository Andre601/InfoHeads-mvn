package me.harry0198.infoheads.commands.general.conversations.wizard;

import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.MessagePrompt;
import org.bukkit.conversations.Prompt;
import org.bukkit.entity.Player;

import me.harry0198.infoheads.InfoHeads;

import java.util.List;

public class ExecutedPrompt extends MessagePrompt {

	public String getPromptText(ConversationContext context) {
		List<String> commands = (List<String>) context.getSessionData("commands");
		List<String> messages = (List<String>) context.getSessionData("messages");

		getInstance().namedComplete.add((Player) context.getForWhom());

		String uuid = java.util.UUID.randomUUID().toString();
		uuid = uuid.substring(0, Math.min(uuid.length(), 5));
		getInstance().uuid.put((Player) context.getForWhom(), uuid);

		getInstance().getConfig().set("Infoheads." + uuid + ".messages", messages);
		getInstance().getConfig().set("Infoheads." + uuid + ".commands", commands);
		getInstance().saveConfig();

		return "Now you may place your infohead / desired block!";
	}

	@Override
	protected Prompt getNextPrompt(ConversationContext context) {
		return Prompt.END_OF_CONVERSATION;
	}

	public InfoHeads getInstance() {
		return InfoHeads.getInstance();
	}
}
