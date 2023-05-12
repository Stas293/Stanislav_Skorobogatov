package org.projects.development.model;

import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Entries {
    private List<Entry> entries;

    private String cursor;

    @JsonProperty("has_more")
    private boolean hasMore;
}
