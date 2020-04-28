/**
 * Copyright (c) 2015-present, Facebook, Inc. All rights reserved.
 *
 * You are hereby granted a non-exclusive, worldwide, royalty-free license to
 * use, copy, modify, and distribute this software in source code or binary
 * form for use in connection with the web services and APIs provided by
 * Facebook.
 *
 * As with any software that integrates with the Facebook platform, your use
 * of this software is subject to the Facebook Developer Principles and
 * Policies [http://developers.facebook.com/policy/]. This copyright notice
 * shall be included in all copies or substantial portions of the software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 *
 */

package com.facebook.ads.sdk;

import java.io.File;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Function;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import com.facebook.ads.sdk.APIException.MalformedResponseException;

/**
 * This class is auto-generated.
 *
 * For any issues or feature requests related to this class, please let us know
 * on github and we'll fix in our codegen framework. We'll not be able to accept
 * pull request for this class.
 *
 */
public class AdCustomizationRuleSpec extends APINode {
  @SerializedName("caption")
  private String mCaption = null;
  @SerializedName("customization_spec")
  private Object mCustomizationSpec = null;
  @SerializedName("description")
  private String mDescription = null;
  @SerializedName("image_hash")
  private String mImageHash = null;
  @SerializedName("link")
  private String mLink = null;
  @SerializedName("message")
  private String mMessage = null;
  @SerializedName("name")
  private String mName = null;
  @SerializedName("priority")
  private Long mPriority = null;
  @SerializedName("template_url_spec")
  private AdCreativeTemplateURLSpec mTemplateUrlSpec = null;
  @SerializedName("video_id")
  private Long mVideoId = null;
  protected static Gson gson = null;

  public AdCustomizationRuleSpec() {
  }

  public String getId() {
    return null;
  }
  public static AdCustomizationRuleSpec loadJSON(String json, APIContext context, String header) {
    AdCustomizationRuleSpec adCustomizationRuleSpec = getGson().fromJson(json, AdCustomizationRuleSpec.class);
    if (context.isDebug()) {
      JsonParser parser = new JsonParser();
      JsonElement o1 = parser.parse(json);
      JsonElement o2 = parser.parse(adCustomizationRuleSpec.toString());
      if (o1.getAsJsonObject().get("__fb_trace_id__") != null) {
        o2.getAsJsonObject().add("__fb_trace_id__", o1.getAsJsonObject().get("__fb_trace_id__"));
      }
      if (!o1.equals(o2)) {
        context.log("[Warning] When parsing response, object is not consistent with JSON:");
        context.log("[JSON]" + o1);
        context.log("[Object]" + o2);
      }
    }
    adCustomizationRuleSpec.context = context;
    adCustomizationRuleSpec.rawValue = json;
    adCustomizationRuleSpec.header = header;
    return adCustomizationRuleSpec;
  }

  public static APINodeList<AdCustomizationRuleSpec> parseResponse(String json, APIContext context, APIRequest request, String header) throws MalformedResponseException {
    APINodeList<AdCustomizationRuleSpec> adCustomizationRuleSpecs = new APINodeList<AdCustomizationRuleSpec>(request, json, header);
    JsonArray arr;
    JsonObject obj;
    JsonParser parser = new JsonParser();
    Exception exception = null;
    try{
      JsonElement result = parser.parse(json);
      if (result.isJsonArray()) {
        // First, check if it's a pure JSON Array
        arr = result.getAsJsonArray();
        for (int i = 0; i < arr.size(); i++) {
          adCustomizationRuleSpecs.add(loadJSON(arr.get(i).getAsJsonObject().toString(), context, header));
        };
        return adCustomizationRuleSpecs;
      } else if (result.isJsonObject()) {
        obj = result.getAsJsonObject();
        if (obj.has("data")) {
          if (obj.has("paging")) {
            JsonObject paging = obj.get("paging").getAsJsonObject();
            if (paging.has("cursors")) {
                JsonObject cursors = paging.get("cursors").getAsJsonObject();
                String before = cursors.has("before") ? cursors.get("before").getAsString() : null;
                String after = cursors.has("after") ? cursors.get("after").getAsString() : null;
                adCustomizationRuleSpecs.setCursors(before, after);
            }
            String previous = paging.has("previous") ? paging.get("previous").getAsString() : null;
            String next = paging.has("next") ? paging.get("next").getAsString() : null;
            adCustomizationRuleSpecs.setPaging(previous, next);
            if (context.hasAppSecret()) {
              adCustomizationRuleSpecs.setAppSecret(context.getAppSecretProof());
            }
          }
          if (obj.get("data").isJsonArray()) {
            // Second, check if it's a JSON array with "data"
            arr = obj.get("data").getAsJsonArray();
            for (int i = 0; i < arr.size(); i++) {
              adCustomizationRuleSpecs.add(loadJSON(arr.get(i).getAsJsonObject().toString(), context, header));
            };
          } else if (obj.get("data").isJsonObject()) {
            // Third, check if it's a JSON object with "data"
            obj = obj.get("data").getAsJsonObject();
            boolean isRedownload = false;
            for (String s : new String[]{"campaigns", "adsets", "ads"}) {
              if (obj.has(s)) {
                isRedownload = true;
                obj = obj.getAsJsonObject(s);
                for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
                  adCustomizationRuleSpecs.add(loadJSON(entry.getValue().toString(), context, header));
                }
                break;
              }
            }
            if (!isRedownload) {
              adCustomizationRuleSpecs.add(loadJSON(obj.toString(), context, header));
            }
          }
          return adCustomizationRuleSpecs;
        } else if (obj.has("images")) {
          // Fourth, check if it's a map of image objects
          obj = obj.get("images").getAsJsonObject();
          for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
              adCustomizationRuleSpecs.add(loadJSON(entry.getValue().toString(), context, header));
          }
          return adCustomizationRuleSpecs;
        } else {
          // Fifth, check if it's an array of objects indexed by id
          boolean isIdIndexedArray = true;
          for (Map.Entry entry : obj.entrySet()) {
            String key = (String) entry.getKey();
            if (key.equals("__fb_trace_id__")) {
              continue;
            }
            JsonElement value = (JsonElement) entry.getValue();
            if (
              value != null &&
              value.isJsonObject() &&
              value.getAsJsonObject().has("id") &&
              value.getAsJsonObject().get("id") != null &&
              value.getAsJsonObject().get("id").getAsString().equals(key)
            ) {
              adCustomizationRuleSpecs.add(loadJSON(value.toString(), context, header));
            } else {
              isIdIndexedArray = false;
              break;
            }
          }
          if (isIdIndexedArray) {
            return adCustomizationRuleSpecs;
          }

          // Sixth, check if it's pure JsonObject
          adCustomizationRuleSpecs.clear();
          adCustomizationRuleSpecs.add(loadJSON(json, context, header));
          return adCustomizationRuleSpecs;
        }
      }
    } catch (Exception e) {
      exception = e;
    }
    throw new MalformedResponseException(
      "Invalid response string: " + json,
      exception
    );
  }

  @Override
  public APIContext getContext() {
    return context;
  }

  @Override
  public void setContext(APIContext context) {
    this.context = context;
  }

  @Override
  public String toString() {
    return getGson().toJson(this);
  }


  public String getFieldCaption() {
    return mCaption;
  }

  public AdCustomizationRuleSpec setFieldCaption(String value) {
    this.mCaption = value;
    return this;
  }

  public Object getFieldCustomizationSpec() {
    return mCustomizationSpec;
  }

  public AdCustomizationRuleSpec setFieldCustomizationSpec(Object value) {
    this.mCustomizationSpec = value;
    return this;
  }

  public String getFieldDescription() {
    return mDescription;
  }

  public AdCustomizationRuleSpec setFieldDescription(String value) {
    this.mDescription = value;
    return this;
  }

  public String getFieldImageHash() {
    return mImageHash;
  }

  public AdCustomizationRuleSpec setFieldImageHash(String value) {
    this.mImageHash = value;
    return this;
  }

  public String getFieldLink() {
    return mLink;
  }

  public AdCustomizationRuleSpec setFieldLink(String value) {
    this.mLink = value;
    return this;
  }

  public String getFieldMessage() {
    return mMessage;
  }

  public AdCustomizationRuleSpec setFieldMessage(String value) {
    this.mMessage = value;
    return this;
  }

  public String getFieldName() {
    return mName;
  }

  public AdCustomizationRuleSpec setFieldName(String value) {
    this.mName = value;
    return this;
  }

  public Long getFieldPriority() {
    return mPriority;
  }

  public AdCustomizationRuleSpec setFieldPriority(Long value) {
    this.mPriority = value;
    return this;
  }

  public AdCreativeTemplateURLSpec getFieldTemplateUrlSpec() {
    return mTemplateUrlSpec;
  }

  public AdCustomizationRuleSpec setFieldTemplateUrlSpec(AdCreativeTemplateURLSpec value) {
    this.mTemplateUrlSpec = value;
    return this;
  }

  public AdCustomizationRuleSpec setFieldTemplateUrlSpec(String value) {
    Type type = new TypeToken<AdCreativeTemplateURLSpec>(){}.getType();
    this.mTemplateUrlSpec = AdCreativeTemplateURLSpec.getGson().fromJson(value, type);
    return this;
  }
  public Long getFieldVideoId() {
    return mVideoId;
  }

  public AdCustomizationRuleSpec setFieldVideoId(Long value) {
    this.mVideoId = value;
    return this;
  }




  synchronized /*package*/ static Gson getGson() {
    if (gson != null) {
      return gson;
    } else {
      gson = new GsonBuilder()
        .excludeFieldsWithModifiers(Modifier.STATIC)
        .excludeFieldsWithModifiers(Modifier.PROTECTED)
        .disableHtmlEscaping()
        .create();
    }
    return gson;
  }

  public AdCustomizationRuleSpec copyFrom(AdCustomizationRuleSpec instance) {
    this.mCaption = instance.mCaption;
    this.mCustomizationSpec = instance.mCustomizationSpec;
    this.mDescription = instance.mDescription;
    this.mImageHash = instance.mImageHash;
    this.mLink = instance.mLink;
    this.mMessage = instance.mMessage;
    this.mName = instance.mName;
    this.mPriority = instance.mPriority;
    this.mTemplateUrlSpec = instance.mTemplateUrlSpec;
    this.mVideoId = instance.mVideoId;
    this.context = instance.context;
    this.rawValue = instance.rawValue;
    return this;
  }

  public static APIRequest.ResponseParser<AdCustomizationRuleSpec> getParser() {
    return new APIRequest.ResponseParser<AdCustomizationRuleSpec>() {
      public APINodeList<AdCustomizationRuleSpec> parseResponse(String response, APIContext context, APIRequest<AdCustomizationRuleSpec> request, String header) throws MalformedResponseException {
        return AdCustomizationRuleSpec.parseResponse(response, context, request, header);
      }
    };
  }
}
