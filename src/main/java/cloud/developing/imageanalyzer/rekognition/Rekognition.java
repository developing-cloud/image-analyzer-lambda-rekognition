package cloud.developing.imageanalyzer.rekognition;

import static java.util.stream.Collectors.joining;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.DetectLabelsRequest;
import com.amazonaws.services.rekognition.model.DetectLabelsResult;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.S3Object;

public class Rekognition {

	private final AmazonRekognition rekognition = AmazonRekognitionClientBuilder.defaultClient();

	public String recognize(S3Object s3Object) throws Exception {
		DetectLabelsResult result = rekognition.detectLabels(
				new DetectLabelsRequest().withImage(new Image().withS3Object(s3Object)).withMinConfidence(70.0f));
		return result.getLabels().stream().map(label -> label.getName()).distinct().collect(joining(", "));
	}

}
