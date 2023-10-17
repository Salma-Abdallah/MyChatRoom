// package gov.iti.jets.utilities;

// import java.nio.charset.StandardCharsets;

// import org.modelmapper.Converter;
// import org.modelmapper.spi.MappingContext;

// public class StringToByteArrayConverter implements Converter<String, byte[]> {
   

//     @Override
//     public byte[] convert(MappingContext<String, byte[]> context) {
//         String source = context.getSource();
//         if (source != null) {
//             return source.getBytes(StandardCharsets.UTF_8);
//         } else {
//             return null;
//         }
//     }
// }
