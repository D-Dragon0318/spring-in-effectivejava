package tacos.simpleflow;

import java.io.File;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;

@Configuration
public class FileWriterIntegrationConfig {
	
	/**
	 * 转换器
	 * @return
	 */
	@Bean
	@Transformer(inputChannel = "textInChannel", outputChannel = "fileWriterChannel")
	public GenericTransformer<String, String> upperCaseTransformer() {
		return text -> text.toUpperCase();
	}
	
	/**
	 * 文件写入消息处理器。
	 * @return
	 */
	@Bean
	@ServiceActivator(inputChannel = "fileWriterChannel")
	public FileWritingMessageHandler fileWriter() {
		FileWritingMessageHandler handler = new FileWritingMessageHandler(new File("/tmp/code/files"));
		handler.setExpectReply(false);
		handler.setFileExistsMode(FileExistsMode.APPEND);
		handler.setAppendNewLine(true);
		return handler;
	}

}