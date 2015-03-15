<div class="form-group">
    <label for="year">Year</label>
    <input type="text" value="${referenceCommand?.year}" name="year" class="form-control"
           id="year" placeholder="Not set">
</div>

<div class="form-group">
    <label for="citation">Citation</label>
    <input type="text" value="${referenceCommand?.citation}" name="citation"
           class="form-control" id="citation" placeholder="Not set">
</div>

<div class="form-group">
    <label for="content">Content</label>
    <textarea name="content" rows="10" id="content"
              class="form-control" placeholder="Not set">${referenceCommand?.content}</textarea>
</div>
<button type="submit" class="btn btn-primary">Submit</button>
