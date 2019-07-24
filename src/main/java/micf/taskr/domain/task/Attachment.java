package micf.taskr.domain.task;

import com.fasterxml.jackson.annotation.JsonBackReference;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

import java.util.Date;

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

    @ManyToOne
    @JoinColumn(name = "task_id")
    @JsonBackReference
    private Task task;

    public Attachment() {}

    public String getId() {
        return id;
      }
    
      public void setId(String id) {
        this.id = id;
      }
    
      public String getType() {
        return type;
      }
    
      public void setType(String type) {
        this.type = type;
      }
    
      public String getSource() {
        return source;
      }
    
      public void setSource(String source) {
        this.source = source;
      }
    
      public String getDescription() {
        return description;
      }
    
      public void setDescription(String description) {
        this.description = description;
      }
    
      public String getFileName() {
        return fileName;
      }
    
      public void setFileName(String fileName) {
        this.fileName = fileName;
      }
    
      public String getTitle() {
        return title;
      }
    
      public void setTitle(String title) {
        this.title = title;
      }
    
      public String getSize() {
        return size;
      }
    
      public void setSize(String size) {
        this.size = size;
      }
    
      public String getFileExtension() {
        return fileExtension;
      }
    
      public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
      }
    
      public String getUrl() {
        return url;
      }
    
      public void setUrl(String url) {
        this.url = url;
      }
    
      public String getFileKey() { return fileKey; }
    
      public void setFileKey(String fileKey) { this.fileKey = fileKey; }
    
      public String getCreatedBy() {
        return createdBy;
      }
    
      public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
      }
    
      public Date getCreatedDate() {
        return createdDate;
      }
    
      public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
      }
    
      public String getLastModifiedBy() {
        return lastModifiedBy;
      }
    
      public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
      }
    
      public Date getLastModifiedDate() {
        return lastModifiedDate;
      }
    
      public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
      }

      public Task getTask() {
        return task;
      }

      public void setTask(Task task) {
          this.task = task;
      }
}