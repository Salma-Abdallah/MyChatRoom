package gov.iti.jets.dtos;


import java.io.Serializable;
import java.sql.Timestamp;

public class Message implements Serializable {
    private static final long serialVersionUID = 4609424488795724384L;
    private Integer id;
    private User author;
    private String chatId;
    private String fontStyle;
    private String fontColor;
    private double fontSize;
    private boolean bold;
    private boolean italic;
    private boolean underlined;
    private String textBackground;
    private Timestamp sentAt;
    private String content;
    private String fileUrl;
    private boolean seen;

    public Message(Integer id, User author, String chatId, String fontStyle, String fontColor, double fontSize, boolean bold,
                   boolean italic, boolean underlined, String textBackground,
                   Timestamp sentAt, String content, String fileUrl, boolean seen) {
        this.id = id;
        this.author = author;
        this.chatId = chatId;
        this.fontStyle = fontStyle;
        this.fontColor = fontColor;
        this.fontSize = fontSize;
        this.bold = bold;
        this.italic = italic;
        this.underlined = underlined;
        this.textBackground = textBackground;
        this.sentAt = sentAt;
        this.content = content;
        this.fileUrl = fileUrl;
        this.seen = seen;
    }

    public Message(User author, String chatId, String fontStyle, String fontColor, double fontSize, boolean bold,
                   boolean italic, boolean underlined, String textBackground, Timestamp sentAt,
                   String content, String fileUrl, boolean seen) {
        this.author = author;
        this.chatId = chatId;
        this.fontStyle = fontStyle;
        this.fontColor = fontColor;
        this.fontSize = fontSize;
        this.bold = bold;
        this.italic = italic;
        this.underlined = underlined;
        this.textBackground = textBackground;
        this.sentAt = sentAt;
        this.content = content;
        this.fileUrl = fileUrl;
        this.seen = seen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public double getFontSize() {
        return fontSize;
    }

    public void setFontSize(double fontSize) {
        this.fontSize = fontSize;
    }

    public boolean isBold() {
        return bold;
    }

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public boolean isItalic() {
        return italic;
    }

    public void setItalic(boolean italic) {
        this.italic = italic;
    }

    public boolean isUnderlined() {
        return underlined;
    }

    public void setUnderlined(boolean underlined) {
        this.underlined = underlined;
    }

    public String getTextBackground() {
        return textBackground;
    }

    public void setTextBackground(String textBackground) {
        this.textBackground = textBackground;
    }

    public Timestamp getSentAt() {
        return sentAt;
    }

    public void setSentAt(Timestamp sentAt) {
        this.sentAt = sentAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", author=" + author +
                ", chatId='" + chatId + '\'' +
                ", fontStyle='" + fontStyle + '\'' +
                ", fontColor='" + fontColor + '\'' +
                ", fontSize=" + fontSize +
                ", bold=" + bold +
                ", italic=" + italic +
                ", underlined=" + underlined +
                ", textBackground='" + textBackground + '\'' +
                ", sentAt=" + sentAt +
                ", content='" + content + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", seen='" + seen + '\'' +
                '}';
    }
}