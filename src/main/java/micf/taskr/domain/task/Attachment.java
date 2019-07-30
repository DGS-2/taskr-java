package micf.taskr.domain.task;

import com.fasterxml.jackson.annotation.JsonBackReference;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import java.util.Date;
import java.util.Objects;

@MappedSuperclass
public class Attachment {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private String type;
    private String source;
    private String description;
    private String fileName;
    private String title;
    private String size;
    private String fileExtension;
    private String url;
    private String fileKey;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private Date createdDate;

    @LastModifiedBy
    private String lastModifiedBy;

    @LastModifiedDate
    private Date lastModifiedDate;

    private String taskIdentifier;


  public Attachment() {
  }

  public Attachment(String id, String type, String source, String description, String fileName, String title, String size, String fileExtension, String url, String fileKey, String createdBy, Date createdDate, String lastModifiedBy, Date lastModifiedDate, String taskIdentifier) {
    this.id = id;
    this.type = type;
    this.source = source;
    this.description = description;
    this.fileName = fileName;
    this.title = title;
    this.size = size;
    this.fileExtension = fileExtension;
    this.url = url;
    this.fileKey = fileKey;
    this.createdBy = createdBy;
    this.createdDate = createdDate;
    this.lastModifiedBy = lastModifiedBy;
    this.lastModifiedDate = lastModifiedDate;
    this.taskIdentifier = taskIdentifier;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getSource() {
    return this.source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getFileName() {
    return this.fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getSize() {
    return this.size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public String getFileExtension() {
    return this.fileExtension;
  }

  public void setFileExtension(String fileExtension) {
    this.fileExtension = fileExtension;
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getFileKey() {
    return this.fileKey;
  }

  public void setFileKey(String fileKey) {
    this.fileKey = fileKey;
  }

  public String getCreatedBy() {
    return this.createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public Date getCreatedDate() {
    return this.createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public String getLastModifiedBy() {
    return this.lastModifiedBy;
  }

  public void setLastModifiedBy(String lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
  }

  public Date getLastModifiedDate() {
    return this.lastModifiedDate;
  }

  public void setLastModifiedDate(Date lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }

  public String getTaskIdentifier() {
    return this.taskIdentifier;
  }

  public void setTaskIdentifier(String taskIdentifier) {
    this.taskIdentifier = taskIdentifier;
  }

  public Attachment id(String id) {
    this.id = id;
    return this;
  }

  public Attachment type(String type) {
    this.type = type;
    return this;
  }

  public Attachment source(String source) {
    this.source = source;
    return this;
  }

  public Attachment description(String description) {
    this.description = description;
    return this;
  }

  public Attachment fileName(String fileName) {
    this.fileName = fileName;
    return this;
  }

  public Attachment title(String title) {
    this.title = title;
    return this;
  }

  public Attachment size(String size) {
    this.size = size;
    return this;
  }

  public Attachment fileExtension(String fileExtension) {
    this.fileExtension = fileExtension;
    return this;
  }

  public Attachment url(String url) {
    this.url = url;
    return this;
  }

  public Attachment fileKey(String fileKey) {
    this.fileKey = fileKey;
    return this;
  }

  public Attachment createdBy(String createdBy) {
    this.createdBy = createdBy;
    return this;
  }

  public Attachment createdDate(Date createdDate) {
    this.createdDate = createdDate;
    return this;
  }

  public Attachment lastModifiedBy(String lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
    return this;
  }

  public Attachment lastModifiedDate(Date lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
    return this;
  }

  public Attachment taskIdentifier(String taskIdentifier) {
    this.taskIdentifier = taskIdentifier;
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Attachment)) {
            return false;
        }
        Attachment attachment = (Attachment) o;
        return Objects.equals(id, attachment.id) && Objects.equals(type, attachment.type) && Objects.equals(source, attachment.source) && Objects.equals(description, attachment.description) && Objects.equals(fileName, attachment.fileName) && Objects.equals(title, attachment.title) && Objects.equals(size, attachment.size) && Objects.equals(fileExtension, attachment.fileExtension) && Objects.equals(url, attachment.url) && Objects.equals(fileKey, attachment.fileKey) && Objects.equals(createdBy, attachment.createdBy) && Objects.equals(createdDate, attachment.createdDate) && Objects.equals(lastModifiedBy, attachment.lastModifiedBy) && Objects.equals(lastModifiedDate, attachment.lastModifiedDate) && Objects.equals(taskIdentifier, attachment.taskIdentifier);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, source, description, fileName, title, size, fileExtension, url, fileKey, createdBy, createdDate, lastModifiedBy, lastModifiedDate, taskIdentifier);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", type='" + getType() + "'" +
      ", source='" + getSource() + "'" +
      ", description='" + getDescription() + "'" +
      ", fileName='" + getFileName() + "'" +
      ", title='" + getTitle() + "'" +
      ", size='" + getSize() + "'" +
      ", fileExtension='" + getFileExtension() + "'" +
      ", url='" + getUrl() + "'" +
      ", fileKey='" + getFileKey() + "'" +
      ", createdBy='" + getCreatedBy() + "'" +
      ", createdDate='" + getCreatedDate() + "'" +
      ", lastModifiedBy='" + getLastModifiedBy() + "'" +
      ", lastModifiedDate='" + getLastModifiedDate() + "'" +
      ", taskIdentifier='" + getTaskIdentifier() + "'" +
      "}";
  }
    
}